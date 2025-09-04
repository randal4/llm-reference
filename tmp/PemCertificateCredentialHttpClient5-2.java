import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.util.Base64;
import com.nimbusds.jose.util.Base64URL;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.apache.hc.client5.http.auth.AuthScope;
import org.apache.hc.client5.http.auth.UsernamePasswordCredentials;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.entity.UrlEncodedFormEntity;
import org.apache.hc.client5.http.impl.auth.BasicCredentialsProvider;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpHost;
import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.message.BasicNameValuePair;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;

import java.io.FileReader;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.PrivateKey;
import java.security.Security;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.*;

public class PemCertificateCredentialHttpClient5 {

    private final String tenantId;
    private final String clientId;
    private final PrivateKey privateKey;
    private final List<X509Certificate> certificates;
    private final HttpHost proxy;
    private final String proxyUser;
    private final String proxyPass;

    public PemCertificateCredentialHttpClient5(String tenantId,
                                               String clientId,
                                               String pemPath,
                                               HttpHost proxy,
                                               String proxyUser,
                                               String proxyPass) throws Exception {
        this.tenantId = tenantId;
        this.clientId = clientId;
        this.proxy = proxy;
        this.proxyUser = proxyUser;
        this.proxyPass = proxyPass;

        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());

        // Parse PEM
        PrivateKey key = null;
        List<X509Certificate> certs = new ArrayList<>();
        try (PEMParser parser = new PEMParser(new FileReader(pemPath))) {
            Object obj;
            JcaPEMKeyConverter converter = new JcaPEMKeyConverter().setProvider("BC");
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            while ((obj = parser.readObject()) != null) {
                if (obj instanceof org.bouncycastle.asn1.pkcs.PrivateKeyInfo) {
                    key = converter.getPrivateKey((org.bouncycastle.asn1.pkcs.PrivateKeyInfo) obj);
                } else if (obj instanceof X509CertificateHolder) {
                    byte[] encoded = ((X509CertificateHolder) obj).getEncoded();
                    certs.add((X509Certificate) cf.generateCertificate(new java.io.ByteArrayInputStream(encoded)));
                }
            }
        }

        if (key == null || certs.isEmpty()) {
            throw new IllegalArgumentException("PEM must contain a private key and at least one certificate");
        }

        this.privateKey = key;
        this.certificates = certs;
    }

    private String buildClientAssertion() throws Exception {
        X509Certificate leafCert = certificates.get(0);

        // Compute SHA-1 thumbprint
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        byte[] digest = md.digest(leafCert.getEncoded());
        Base64URL thumbprint = new Base64URL(Base64.getUrlEncoder().withoutPadding().encodeToString(digest));

        // Build x5c array
        List<Base64> x5cList = new ArrayList<>();
        for (X509Certificate cert : certificates) {
            x5cList.add(new Base64(java.util.Base64.getEncoder().encodeToString(cert.getEncoded())));
        }

        // JWS header
        JWSHeader header = new JWSHeader.Builder(JWSAlgorithm.RS256)
                .x509CertChain(x5cList)
                .x509CertSHA1Thumbprint(thumbprint)
                .build();

        long now = System.currentTimeMillis();
        JWTClaimsSet claims = new JWTClaimsSet.Builder()
                .issuer(clientId)
                .subject(clientId)
                .audience("https://login.microsoftonline.com/" + tenantId + "/oauth2/v2.0/token")
                .issueTime(new Date(now))
                .expirationTime(new Date(now + 5 * 60 * 1000)) // 5 min expiry
                .jwtID(UUID.randomUUID().toString())
                .build();

        SignedJWT signedJWT = new SignedJWT(header, claims);
        signedJWT.sign(new RSASSASigner(privateKey));

        return signedJWT.serialize();
    }

    public String acquireToken(String scope) throws Exception {
        String jwt = buildClientAssertion();

        HttpPost post = new HttpPost("https://login.microsoftonline.com/" + tenantId + "/oauth2/v2.0/token");

        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("client_id", clientId));
        params.add(new BasicNameValuePair("scope", scope));
        params.add(new BasicNameValuePair("grant_type", "client_credentials"));
        params.add(new BasicNameValuePair("client_assertion_type",
                "urn:ietf:params:oauth:client-assertion-type:jwt-bearer"));
        params.add(new BasicNameValuePair("client_assertion", jwt));

        post.setEntity(new UrlEncodedFormEntity(params));

        BasicCredentialsProvider credsProvider = new BasicCredentialsProvider();
        if (proxy != null && proxyUser != null) {
            credsProvider.setCredentials(
                    new AuthScope(proxy),
                    new UsernamePasswordCredentials(proxyUser, proxyPass.toCharArray())
            );
        }

        try (CloseableHttpClient client = HttpClients.custom()
                .setProxy(proxy)
                .setDefaultCredentialsProvider(credsProvider)
                .build()) {

            return client.execute(post, response -> EntityUtils.toString(response.getEntity()));
        }
    }

    public static void main(String[] args) throws Exception {
        String tenantId = "<tenant_id>";
        String clientId = "<client_id>";
        String pemPath = "client.pem"; // private key + cert(s)
        HttpHost proxy = new HttpHost("https", "proxy.example.com", 443);
        String proxyUser = "proxyuser";
        String proxyPass = "proxypass";

        PemCertificateCredentialHttpClient5 credential =
                new PemCertificateCredentialHttpClient5(tenantId, clientId, pemPath, proxy, proxyUser, proxyPass);

        String tokenResponse = credential.acquireToken("https://graph.microsoft.com/.default");
        System.out.println("Token response:\n" + tokenResponse);
    }
}
