package com.mertkaracam.redisnlpdocservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
public class SignatureController {

	@Autowired
    private SignatureVerificationService verificationService;

    public SignatureController(SignatureVerificationService verificationService) {
        this.verificationService = verificationService;
    }

    @PostMapping("/verify-signature")
    public ResponseEntity<String> verifySignature(@RequestParam("file") MultipartFile file) {
        try {
            byte[] pdfContent = file.getBytes();
            boolean isVerified = verificationService.verifyDocumentSignature(pdfContent);

            if (isVerified) {
                return ResponseEntity.ok("İmza doğrulandı!");
            } else {
                return ResponseEntity.badRequest().body("İmza doğrulanamadı!");
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Bir hata oluştu: " + e.getMessage());
        }
    }
}
