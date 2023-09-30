package com.mertkaracam.redisnlpdocservice.repository;

import com.mertkaracam.redisnlpdocservice.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<Document, Long> {
}
