import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.security.PublicKey;
import java.security.Signature;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.interactive.digitalsignature.PDSignature;

public class SignatureVerificationService {

    public boolean verifyDocumentSignature(byte[] pdfContent) {
        try (PDDocument document = PDDocument.load(pdfContent)) {
            PDSignature signature = document.getLastSignatureDictionary();
            if (signature == null) {
                return false; // Belgede imza bulunamadı
            }

            CertificateFactory factory = CertificateFactory.getInstance("X.509");
            InputStream inStream = new ByteArrayInputStream(signature.getContents(pdfContent));
            Certificate certificate = factory.generateCertificate(inStream);
            PublicKey publicKey = certificate.getPublicKey();

            // İmza doğrulama işlemi
            Signature sig = Signature.getInstance(signature.getFilter());
            sig.initVerify(publicKey);
            sig.update(signature.getSignedContent(pdfContent));

            return sig.verify(signature.getContents(pdfContent));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
