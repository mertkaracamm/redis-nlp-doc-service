package com.mertkaracam.redisnlpdocservice.service;

import com.mertkaracam.redisnlpdocservice.model.DocumentAnalysis;
import com.mertkaracam.redisnlpdocservice.repository.DocumentAnalysisRepository;
import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.util.Span;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

@Service
public class DocumentAnalysisService {

    @Autowired
    private DocumentAnalysisRepository repository;

    public DocumentAnalysis analyzeAndSaveWithFile(DocumentAnalysis analysis) {
        String content = new String(analysis.getFileData());

        try {
            // Tokenizasyon
        	InputStream tokenModelIn = Thread.currentThread().getContextClassLoader().getResourceAsStream("models/en-token.bin");
            TokenizerModel tokenModel = new TokenizerModel(tokenModelIn);
            TokenizerME tokenizer = new TokenizerME(tokenModel);
            String[] tokens = tokenizer.tokenize(content);

            // Named Entity Recognition (NER)
            InputStream nameModelIn = Thread.currentThread().getContextClassLoader().getResourceAsStream("models/en-ner-person.bin");
            TokenNameFinderModel nameModel = new TokenNameFinderModel(nameModelIn);
            NameFinderME nameFinder = new NameFinderME(nameModel);
            Span[] nameSpans = nameFinder.find(tokens);
            String[] names = Arrays.stream(nameSpans).map(span -> tokens[span.getStart()]).toArray(String[]::new);

            // Analiz sonuçlarını birleştirme
            StringBuilder analysisResult = new StringBuilder();
            analysisResult.append("Kelimeler: ").append(String.join(", ", tokens)).append("\n");
            analysisResult.append("İsimler: ").append(String.join(", ", names)).append("\n");

            analysis.setAnalysisResult(analysisResult.toString());
            repository.save(analysis);
            return analysis;
        } catch (IOException e) {
            throw new RuntimeException("NLP model dosyalarını okurken bir hata oluştu.", e);
        }
    }

    public DocumentAnalysis analyzeAndSave(String content) {
        DocumentAnalysis analysis = new DocumentAnalysis();
        analysis.setContent(content);
        analysis.setAnalysisResult("Analiz sonucu: " + content);
        repository.save(analysis);
        return analysis;
    }

    public DocumentAnalysis findById(String id) {
        return repository.findById(id);
    }
}