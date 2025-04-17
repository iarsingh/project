package com.naukrisetu.repository;

import com.naukrisetu.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {
    
    List<Document> findByUserId(Long userId);
    
    Optional<Document> findByUserIdAndDocumentType(Long userId, Document.DocumentType documentType);
    
    List<Document> findByUserIdAndIsVerifiedFalse(Long userId);
    
    List<Document> findByIsVerifiedFalse();

    // Statistics methods
    long countByIsVerifiedTrue();
    
    long countByIsVerifiedFalse();
    
    long countByCreatedAtAfter(LocalDateTime startDate);
} 