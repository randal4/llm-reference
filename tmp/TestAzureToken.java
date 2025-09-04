public class TestAzureToken {
    public static void main(String[] args) throws Exception {
        HttpHost proxy = new HttpHost("https", "proxy.example.com", 443);

        HttpClient5CertificateCredential cred = new HttpClient5CertificateCredential(
                "<tenant_id>",
                "<client_id>",
                "https://management.azure.com/.default",
                "path/to/cert.pfx",
                "certPassword",
                proxy);

        String token = cred.getToken();
        System.out.println("Access Token: " + token);
    }
}
