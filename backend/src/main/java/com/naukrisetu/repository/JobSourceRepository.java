package com.naukrisetu.repository;

import com.naukrisetu.model.JobSource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface JobSourceRepository extends JpaRepository<JobSource, Long> {
    
    List<JobSource> findByIsActiveTrue();
    
    @Query("SELECT js FROM JobSource js WHERE js.isActive = true AND (js.lastScrapedAt IS NULL OR js.lastScrapedAt < :threshold)")
    List<JobSource> findSourcesForScraping(LocalDateTime threshold);
    
    boolean existsByUrl(String url);
} 