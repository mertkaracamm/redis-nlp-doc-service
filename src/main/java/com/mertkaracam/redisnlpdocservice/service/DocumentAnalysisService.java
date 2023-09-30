package com.mertkaracam.redisnlpdocservice.service;

import com.mertkaracam.redisnlpdocservice.model.DocumentAnalysis;
import com.mertkaracam.redisnlpdocservice.repository.DocumentAnalysisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DocumentAnalysisService {

    @Autowired
    private DocumentAnalysisRepository repository;

    public DocumentAnalysis analyzeAndSave(String content) {
        // Burada NLP analizi yapılacak. Şimdilik basit bir örnek olarak içeriği olduğu gibi döndürelim.
        DocumentAnalysis analysis = new DocumentAnalysis();
        analysis.setContent(content);
        analysis.setAnalysisResult("Analiz sonucu: " + content); // Bu kısmı gerçek NLP analizi ile değiştirebilirsiniz.

        repository.save(analysis);
        return analysis;
    }

    public DocumentAnalysis findById(String id) {
        return repository.findById(id);
    }
}
