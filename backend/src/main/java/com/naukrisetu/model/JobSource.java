package com.naukrisetu.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "job_sources")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobSource {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "name", nullable = false)
    private String name;
    
    @Column(name = "url", nullable = false)
    private String url;
    
    @Column(name = "is_active")
    private boolean isActive = true;
    
    @Column(name = "last_scraped_at")
    private LocalDateTime lastScrapedAt;
    
    @Column(name = "scrape_frequency_minutes")
    private Integer scrapeFrequencyMinutes = 60; // Default to hourly
    
    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;
} 