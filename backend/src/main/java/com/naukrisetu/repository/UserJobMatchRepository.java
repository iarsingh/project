package com.naukrisetu.repository;

import com.naukrisetu.model.UserJobMatch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface UserJobMatchRepository extends JpaRepository<UserJobMatch, Long> {
    
    Page<UserJobMatch> findByUserIdOrderByMatchScoreDesc(Long userId, Pageable pageable);
    
    @Query("SELECT ujm FROM UserJobMatch ujm WHERE ujm.user.id = :userId AND ujm.matchScore >= :minScore AND ujm.isNotified = false")
    List<UserJobMatch> findUnnotifiedMatchesAboveScore(@Param("userId") Long userId, @Param("minScore") Double minScore);
    
    @Query("SELECT COUNT(ujm) FROM UserJobMatch ujm WHERE ujm.user.id = :userId AND ujm.createdAt > :startDate")
    long countRecentMatches(@Param("userId") Long userId, @Param("startDate") LocalDateTime startDate);
    
    boolean existsByUserIdAndJobId(Long userId, Long jobId);
} 