package com.naukrisetu.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "roles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERole name;
    
    // Explicit getter and setter for name field
    public ERole getName() {
        return this.name;
    }
    
    public void setName(ERole name) {
        this.name = name;
    }
    
    public enum ERole {
        ROLE_USER,
        ROLE_ADMIN,
        ROLE_MODERATOR
    }
} 