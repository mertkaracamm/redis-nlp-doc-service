package com.mertkaracam.redisnlpdocservice.repository;

import com.mertkaracam.redisnlpdocservice.model.DocumentAnalysis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class RedisDocumentAnalysisRepository implements DocumentAnalysisRepository {

    private static final String KEY = "DocumentAnalysis";

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public void save(DocumentAnalysis documentAnalysis) {
        if (documentAnalysis.getDocumentId() == null) {
            documentAnalysis.setDocumentId(UUID.randomUUID().toString());
        }
        redisTemplate.opsForHash().put(KEY, documentAnalysis.getDocumentId(), documentAnalysis);
    }

    @Override
    public DocumentAnalysis findById(String id) {
        return (DocumentAnalysis) redisTemplate.opsForHash().get(KEY, id);
    }
}
