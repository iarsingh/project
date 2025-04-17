package com.naukrisetu.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_job_view_history")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserJobViewHistory {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    
    @ManyToOne
    @JoinColumn(name = "job_id", nullable = false)
    private Job job;
    
    @Column(name = "viewed_at")
    @CreationTimestamp
    private LocalDateTime viewedAt;
    
    @Column(name = "view_duration_seconds")
    private Integer viewDurationSeconds;
    
    @Column(name = "source")
    private String source; // e.g., "search", "notification", "referral"
    
    @Column(name = "device_type")
    private String deviceType; // e.g., "mobile", "desktop", "tablet"
} 