package com.naukrisetu.repository;

import com.naukrisetu.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    Optional<User> findByEmail(String email);
    
    Boolean existsByEmail(String email);
    
    Optional<User> findByPhoneNumber(String phoneNumber);
    
    Boolean existsByPhoneNumber(String phoneNumber);

    // Statistics methods
    long countByIsActiveTrue();
    
    long countByIsVerifiedTrue();
    
    long countByCreatedAtAfter(LocalDateTime startDate);
    
    long countByLastLoginAfter(LocalDateTime startDate);
} 