package com.mertkaracam.redisnlpdocservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService {
    private final RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public RedisService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void saveAnalysisResult(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public String getAnalysisResult(String key) {
        return (String) redisTemplate.opsForValue().get(key);
    }
}
