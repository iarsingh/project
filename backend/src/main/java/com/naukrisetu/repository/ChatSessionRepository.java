package com.naukrisetu.repository;

import com.naukrisetu.model.ChatSession;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ChatSessionRepository extends JpaRepository<ChatSession, Long> {
    
    Page<ChatSession> findByUserIdOrderByLastMessageAtDesc(Long userId, Pageable pageable);
    
    @Query("SELECT cs FROM ChatSession cs WHERE cs.user.id = :userId AND cs.isActive = true AND cs.lastMessageAt > :startDate")
    List<ChatSession> findActiveSessions(@Param("userId") Long userId, @Param("startDate") LocalDateTime startDate);
    
    @Query("SELECT COUNT(cs) FROM ChatSession cs WHERE cs.user.id = :userId AND cs.createdAt > :startDate")
    long countRecentSessions(@Param("userId") Long userId, @Param("startDate") LocalDateTime startDate);
} 