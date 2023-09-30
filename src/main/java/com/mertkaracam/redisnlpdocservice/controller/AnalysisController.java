package com.mertkaracam.redisnlpdocservice.controller;

import com.mertkaracam.redisnlpdocservice.service.NLPService;
import com.mertkaracam.redisnlpdocservice.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/analyze")
public class AnalysisController {
    private final NLPService nlpService;
    private final RedisService redisService;

    @Autowired
    public AnalysisController(NLPService nlpService, RedisService redisService) {
        this.nlpService = nlpService;
        this.redisService = redisService;
    }

    @PostMapping
    public ResponseEntity<String> analyzeAndSave(@RequestBody String text) {
        String sentiment = nlpService.analyzeText(text);
        redisService.saveAnalysisResult("analysis:" + text.hashCode(), sentiment);
        return ResponseEntity.ok(sentiment);
    }

    @GetMapping("/{key}")
    public ResponseEntity<String> getAnalysisResult(@PathVariable String key) {
        String result = redisService.getAnalysisResult("analysis:" + key);
        if (result != null) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
