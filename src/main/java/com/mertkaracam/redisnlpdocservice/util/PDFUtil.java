package com.mertkaracam.redisnlpdocservice.util;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class PDFUtil {

    public static String extractTextFromPDF(byte[] pdfData) throws IOException {
        String content = "";
        try (InputStream inputStream = new ByteArrayInputStream(pdfData);
             PDDocument document = PDDocument.load(inputStream)) {
            PDFTextStripper pdfStripper = new PDFTextStripper();
            content = pdfStripper.getText(document);
        }
        return content;
    }
}
