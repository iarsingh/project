package com.naukrisetu.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "jobs")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Job {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank
    @Column(name = "title")
    private String title;
    
    @NotBlank
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;
    
    @NotBlank
    @Column(name = "department")
    private String department;
    
    @NotBlank
    @Column(name = "organization")
    private String organization;
    
    @NotNull
    @Column(name = "district_id")
    private Long districtId;
    
    @Column(name = "qualification_required")
    private String qualificationRequired;
    
    @Column(name = "experience_required")
    private String experienceRequired;
    
    @Column(name = "salary_range")
    private String salaryRange;
    
    @Column(name = "application_deadline")
    private LocalDateTime applicationDeadline;
    
    @Column(name = "job_type")
    @Enumerated(EnumType.STRING)
    private JobType jobType;
    
    @Column(name = "vacancy_count")
    private Integer vacancyCount;
    
    @Column(name = "is_active")
    private boolean isActive = true;
    
    @Column(name = "source_url")
    private String sourceUrl;
    
    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;
    
    @OneToMany(mappedBy = "job", cascade = CascadeType.ALL)
    private Set<JobApplication> applications = new HashSet<>();
    
    @OneToMany(mappedBy = "job", cascade = CascadeType.ALL)
    private Set<Referral> referrals = new HashSet<>();
    
    public enum JobType {
        PERMANENT,
        CONTRACT,
        TEMPORARY,
        INTERNSHIP
    }

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
} 