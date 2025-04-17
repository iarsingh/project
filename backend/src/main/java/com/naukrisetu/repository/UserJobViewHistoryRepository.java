package com.naukrisetu.repository;

import com.naukrisetu.model.UserJobViewHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface UserJobViewHistoryRepository extends JpaRepository<UserJobViewHistory, Long> {
    
    Page<UserJobViewHistory> findByUserIdOrderByViewedAtDesc(Long userId, Pageable pageable);
    
    @Query("SELECT vh FROM UserJobViewHistory vh WHERE vh.user.id = :userId AND vh.viewedAt > :startDate")
    List<UserJobViewHistory> findRecentViews(@Param("userId") Long userId, @Param("startDate") LocalDateTime startDate);
    
    @Query("SELECT COUNT(vh) FROM UserJobViewHistory vh WHERE vh.job.id = :jobId AND vh.viewedAt > :startDate")
    long countRecentViewsForJob(@Param("jobId") Long jobId, @Param("startDate") LocalDateTime startDate);
    
    boolean existsByUserIdAndJobId(Long userId, Long jobId);
} 