package com.mertkaracam.redisnlpdocservice.service;

import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Properties;

@Service
public class NLPService {
    private final StanfordCoreNLP pipeline;

    public NLPService() {
        
    	
    	Properties props = new Properties();
        props.setProperty("annotators", "tokenize, ssplit, pos, lemma, ner, parse, sentiment");
        
        String basePath = System.getProperty("user.dir") + "/src/main/resources/";
       
        props.setProperty("ner.model", "file:" + basePath + "english.all.3class.distsim.crf.ser.gz");
        props.setProperty("pos.model", "file:" + basePath + "english-left3words-distsim.tagger");

        //props.setProperty("pos.model", modelPath); // Model dosyasının yolunu belirtiyoruz

        pipeline = new StanfordCoreNLP(props);
    }

    public String analyzeText(String text) {
        CoreDocument document = new CoreDocument(text);
        pipeline.annotate(document);
        return document.sentences().get(0).sentiment();
    }
}
