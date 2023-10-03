package com.mertkaracam.redisnlpdocservice.controller;

import com.mertkaracam.redisnlpdocservice.service.SignatureVerificationService;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/signature")
public class SignatureController {
    @Autowired
    private SignatureVerificationService signatureVerificationService;

    @PostMapping("/verify")
    public String verifySignature(@RequestParam("file") MultipartFile file) {
        try {
            byte[] pdfBytes = file.getBytes();
            PDDocument document = PDDocument.load(pdfBytes);
            boolean isVerified = signatureVerificationService.verifySignature(document);
            if (isVerified) {
                return "Imza doğrulandı.";
            } else {
                return "Imza doğrulanamadı.";
            }
        } catch (Exception e) {
            return "Hata: " + e.getMessage();
        }
    }

}
