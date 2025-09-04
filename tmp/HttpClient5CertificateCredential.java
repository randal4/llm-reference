import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.apache.hc.client5.http.classic.CloseableHttpClient;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.entity.UrlEncodedFormEntity;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpHost;
import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import java.util.*;

public class HttpClient5CertificateCredential {

    private final String tenantId;
    private final String clientId;
    private final String scope;
    private final KeyStore keyStore;
    private final String alias;
    private final char[] password;
    private final HttpHost proxy; // can be null

    public HttpClient5CertificateCredential(
            String tenantId,
            String clientId,
            String scope,
            String pfxPath,
            String pfxPassword,
            HttpHost proxy) throws Exception {

        this.tenantId = tenantId;
        this.clientId = clientId;
        this.scope = scope;
        this.password = pfxPassword.toCharArray();
        this.proxy = proxy;

        this.keyStore = KeyStore.getInstance("PKCS12");
        try (FileInputStream fis = new FileInputStream(pfxPath)) {
            this.keyStore.load(fis, password.toCharArray());
        }
        this.alias = keyStore.aliases().nextElement();
    }

    private String buildClientAssertion() throws Exception {
        PrivateKey privateKey = (PrivateKey) keyStore.getKey(alias, password);
        X509Certificate cert = (X509Certificate) keyStore.getCertificate(alias);

        JWSHeader header = new JWSHeader.Builder(JWSAlgorithm.RS256)
                .x509CertChain(Collections.singletonList(
                        Base64.getEncoder().encodeToString(cert.getEncoded())))
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

    public String getToken() throws Exception {
        String clientAssertion = buildClientAssertion();

        HttpPost post = new HttpPost("https://login.microsoftonline.com/" + tenantId + "/oauth2/v2.0/token");
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("client_id", clientId));
        params.add(new BasicNameValuePair("scope", scope));
        params.add(new BasicNameValuePair("grant_type", "client_credentials"));
        params.add(new BasicNameValuePair("client_assertion_type",
                "urn:ietf:params:oauth:client-assertion-type:jwt-bearer"));
        params.add(new BasicNameValuePair("client_assertion", clientAssertion));

        post.setEntity(new UrlEncodedFormEntity(params));

        try (CloseableHttpClient client = (proxy != null
                ? HttpClients.custom().setProxy(proxy).build()
                : HttpClients.createDefault())) {

            return client.execute(post, response -> {
                String json = EntityUtils.toString(response.getEntity());
                JSONObject obj = new JSONObject(json);
                return obj.getString("access_token");
            });
        }
    }
}
