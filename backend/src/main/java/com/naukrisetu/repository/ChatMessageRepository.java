package com.naukrisetu.repository;

import com.naukrisetu.model.ChatMessage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
    
    Page<ChatMessage> findBySessionIdOrderByCreatedAtAsc(Long sessionId, Pageable pageable);
    
    @Query("SELECT cm FROM ChatMessage cm WHERE cm.session.id = :sessionId AND cm.createdAt > :startDate")
    List<ChatMessage> findRecentMessages(@Param("sessionId") Long sessionId, @Param("startDate") LocalDateTime startDate);
    
    @Query("SELECT COUNT(cm) FROM ChatMessage cm WHERE cm.session.id = :sessionId AND cm.createdAt > :startDate")
    long countRecentMessages(@Param("sessionId") Long sessionId, @Param("startDate") LocalDateTime startDate);
    
    @Query("SELECT SUM(cm.tokensUsed) FROM ChatMessage cm WHERE cm.session.id = :sessionId")
    Integer calculateTotalTokensUsed(@Param("sessionId") Long sessionId);
} 