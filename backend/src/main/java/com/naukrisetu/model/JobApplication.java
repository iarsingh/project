package com.naukrisetu.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "job_applications")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobApplication {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_id", nullable = false)
    private Job job;
    
    @Column(name = "application_status")
    @Enumerated(EnumType.STRING)
    private ApplicationStatus status = ApplicationStatus.PENDING;
    
    @Column(name = "application_date")
    private LocalDateTime applicationDate;
    
    @Column(name = "last_status_update")
    private LocalDateTime lastStatusUpdate;
    
    @Column(name = "application_notes")
    private String applicationNotes;
    
    @Column(name = "is_offline_submission")
    private boolean isOfflineSubmission = false;
    
    @Column(name = "offline_reference_id")
    private String offlineReferenceId;
    
    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;
    
    @PrePersist
    protected void onCreate() {
        applicationDate = LocalDateTime.now();
        lastStatusUpdate = LocalDateTime.now();
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }
    
    @PreUpdate
    protected void onUpdate() {
        lastStatusUpdate = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }
    
    public enum ApplicationStatus {
        PENDING,
        SUBMITTED,
        UNDER_REVIEW,
        SHORTLISTED,
        REJECTED,
        HIRED
    }
} 