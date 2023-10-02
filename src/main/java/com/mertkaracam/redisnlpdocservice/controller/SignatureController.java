package com.mertkaracam.redisnlpdocservice.controller;

import com.mertkaracam.redisnlpdocservice.service.SignatureVerificationService;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/signature")
public class SignatureController {
    @Autowired
    private SignatureVerificationService signatureVerificationService;

    @PostMapping("/verify")
    public boolean verifySignature(@RequestParam("pdfBytes") byte[] pdfBytes) {
        try {
            PDDocument document = PDDocument.load(pdfBytes);
            return signatureVerificationService.verifySignature(document);
        } catch (Exception e) {
            // Hata durumunda false döndür ve hatayı logla
            e.printStackTrace();
            return false;
        }
    }
}
