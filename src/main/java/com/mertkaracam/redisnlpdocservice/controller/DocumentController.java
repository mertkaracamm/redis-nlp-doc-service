package com.mertkaracam.redisnlpdocservice.controller;

import com.mertkaracam.redisnlpdocservice.model.DocumentAnalysis;
import com.mertkaracam.redisnlpdocservice.service.DocumentAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/documents")
public class DocumentController {

    @Autowired
    private DocumentAnalysisService service;

    @PostMapping("/analyze")
    public DocumentAnalysis analyzeAndSave(@RequestBody String content) {
        return service.analyzeAndSave(content);
    }

    @GetMapping("/{id}")
    public DocumentAnalysis findById(@PathVariable String id) {
        return service.findById(id);
    }
}
