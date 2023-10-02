package com.mertkaracam.redisnlpdocservice.service;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.interactive.digitalsignature.PDSignature;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
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

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            document.save(byteArrayOutputStream);
            InputStream documentInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());

            for (PDSignature signature : signatures) {
                InputStream signedContentStream = new ByteArrayInputStream(signature.getContents(documentInputStream));
                
                CertificateFactory factory = CertificateFactory.getInstance("X.509");
                Certificate certificate = factory.generateCertificate(signedContentStream);
                X509Certificate x509Certificate = (X509Certificate) certificate;
                
                if (x509Certificate != null && isValidCertificate(x509Certificate)) {
                    return true;
                }
            }

            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean isValidCertificate(X509Certificate certificate) {
        try {
            certificate.checkValidity(); // Bu metot, sertifikanın geçerli olup olmadığını kontrol eder.
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
