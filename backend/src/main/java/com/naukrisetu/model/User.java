package com.naukrisetu.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank
    @Size(min = 3, max = 50)
    private String name;
    
    @NotBlank
    @Size(max = 50)
    @Email
    @Column(unique = true)
    private String email;
    
    @NotBlank
    @Size(min = 6, max = 120)
    private String password;
    
    @Column(name = "phone_number", unique = true)
    private String phoneNumber;
    
    @Column(name = "district_id")
    private Long districtId;
    
    @Column(name = "qualification")
    private String qualification;
    
    @Column(name = "skills")
    private String skills;
    
    @Column(name = "profile_completed")
    private boolean profileCompleted = false;
    
    @Column(name = "is_active")
    private boolean isActive = true;
    
    @Column(name = "is_verified")
    private boolean isVerified = false;
    
    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;
    
    @Column(name = "last_login")
    private LocalDateTime lastLogin;
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Document> documents = new HashSet<>();
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<JobApplication> jobApplications = new HashSet<>();
    
    @OneToMany(mappedBy = "referrer", cascade = CascadeType.ALL)
    private Set<Referral> referrals = new HashSet<>();

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