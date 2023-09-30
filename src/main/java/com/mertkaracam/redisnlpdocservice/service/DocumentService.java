package com.mertkaracam.redisnlpdocservice.service;

import com.mertkaracam.redisnlpdocservice.model.Document;
import com.mertkaracam.redisnlpdocservice.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DocumentService {
    private final DocumentRepository documentRepository;

    @Autowired
    public DocumentService(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    public Document save(Document document) {
        return documentRepository.save(document);
    }

    public Document findById(Long id) {
        return documentRepository.findById(id).orElse(null);
    }
}
