package com.naukrisetu.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "referrals")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Referral {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "referrer_id", nullable = false)
    private User referrer;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "referred_id", nullable = false)
    private User referred;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_id", nullable = false)
    private Job job;
    
    @Column(name = "referred_email")
    private String referredEmail;
    
    @Column(name = "referred_name")
    private String referredName;
    
    @Column(name = "referred_phone")
    private String referredPhone;
    
    @Column(name = "referral_status")
    @Enumerated(EnumType.STRING)
    private ReferralStatus status;
    
    @Column(name = "reward_points")
    private Integer rewardPoints = 0;
    
    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime referredAt;
    
    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;
    
    @PrePersist
    protected void onCreate() {
        referredAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
    
    public enum ReferralStatus {
        PENDING,
        ACCEPTED,
        REJECTED,
        HIRED
    }
} 