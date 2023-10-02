package com.mertkaracam.redisnlpdocservice.service;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.interactive.digitalsignature.PDSignature;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.List;

@Service
public class SignatureVerificationService {

    public boolean verifySignature(PDDocument document) {
        try {
            List<PDSignature> signatures = document.getSignatureDictionaries();

            if (signatures == null || signatures.isEmpty()) {
                return false;
            }

            for (PDSignature signature : signatures) {
                // İmza verilerini al
                byte[] signatureBytes = signature.getSignature();
                byte[] signedContent = signature.getSignedContent(document);
                
                // İmza doğrulama işlemi
                CertificateFactory factory = CertificateFactory.getInstance("X.509");
                Certificate certificate = factory.generateCertificate(signature.getCOSObject().getAsStream());
                X509Certificate x509Certificate = (X509Certificate) certificate;
                signature.verifySignature(signedContent, signatureBytes);

                // İmza doğrulandıysa true döndür
                if (x509Certificate != null) {
                    return true;
                }
            }

            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
