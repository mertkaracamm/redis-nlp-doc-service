package com.mertkaracam.redisnlpdocservice.repository;

import com.mertkaracam.redisnlpdocservice.model.DocumentAnalysis;

public interface DocumentAnalysisRepository {
    void save(DocumentAnalysis documentAnalysis);
    DocumentAnalysis findById(String id);
}
