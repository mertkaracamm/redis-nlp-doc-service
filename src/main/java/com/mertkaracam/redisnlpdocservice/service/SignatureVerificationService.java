package com.mertkaracam.redisnlpdocservice.service;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.interactive.digitalsignature.PDSignature;
import org.apache.pdfbox.pdmodel.interactive.digitalsignature.SignatureInterface;
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
            SignatureInterface signatureInterface = document.getSignature();
            if (signatureInterface == null) {
                System.out.println("Belgede imza bilgisi bulunamadı.");
                return false;
            }

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            document.save(byteArrayOutputStream);
            InputStream documentInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());

            InputStream signedContentStream = new ByteArrayInputStream(signatureInterface.getContents(documentInputStream));
                
            CertificateFactory factory = CertificateFactory.getInstance("X.509");
            Certificate certificate = factory.generateCertificate(signedContentStream);
            X509Certificate x509Certificate = (X509Certificate) certificate;
                
            if (x509Certificate != null && isValidCertificate(x509Certificate)) {
                return true;
            }

            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean isValidCertificate(X509Certificate certificate) {
        try {
            certificate.checkValidity();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
