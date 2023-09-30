package com.mertkaracam.redisnlpdocservice.controller;

import com.mertkaracam.redisnlpdocservice.model.DocumentAnalysis;
import com.mertkaracam.redisnlpdocservice.service.DocumentAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/documents")
public class DocumentController {

    @Autowired
    private DocumentAnalysisService service;

    @PostMapping("/upload")
    public DocumentAnalysis uploadAndAnalyze(@RequestParam("file") MultipartFile file) throws IOException {
        byte[] fileData = file.getBytes();
        String fileType = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);

        if (!fileType.equalsIgnoreCase("pdf") && !fileType.equalsIgnoreCase("jpeg") && !fileType.equalsIgnoreCase("png")) {
            throw new IllegalArgumentException("Desteklenmeyen dosya formatı");
        }

        DocumentAnalysis analysis = new DocumentAnalysis();
        analysis.setFileData(fileData);
        analysis.setFileType(fileType);
        return service.analyzeAndSaveWithFile(analysis);
    }

    @PostMapping("/analyze")
    public DocumentAnalysis analyzeAndSave(@RequestBody String content) {
        return service.analyzeAndSave(content);
    }

    @GetMapping("/{id}")
    public DocumentAnalysis findById(@PathVariable String id) {
        return service.findById(id);
    }
}
