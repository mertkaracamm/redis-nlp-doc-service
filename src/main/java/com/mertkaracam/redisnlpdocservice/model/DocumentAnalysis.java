package com.mertkaracam.redisnlpdocservice.model;

import java.io.Serializable;

public class DocumentAnalysis implements Serializable {
    private static final long serialVersionUID = 1L;

    private String documentId;
    private String content;
    private String analysisResult;
    private byte[] fileData;
    private String fileType;

    public DocumentAnalysis() {}

    public DocumentAnalysis(String documentId, String content, String analysisResult) {
        this.documentId = documentId;
        this.content = content;
        this.analysisResult = analysisResult;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAnalysisResult() {
        return analysisResult;
    }

    public void setAnalysisResult(String analysisResult) {
        this.analysisResult = analysisResult;
    }

    public byte[] getFileData() {
        return fileData;
    }

    public void setFileData(byte[] fileData) {
        this.fileData = fileData;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }
}