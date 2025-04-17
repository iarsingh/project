package com.naukrisetu.repository;

import com.naukrisetu.model.AuthOTP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface AuthOTPRepository extends JpaRepository<AuthOTP, Long> {
    
    Optional<AuthOTP> findFirstByPhoneNumberAndIsVerifiedFalseAndIsActiveTrueOrderByCreatedAtDesc(String phoneNumber);
    
    @Query("SELECT a FROM AuthOTP a WHERE a.phoneNumber = :phoneNumber AND a.isActive = true AND a.expiresAt > :now ORDER BY a.createdAt DESC")
    Optional<AuthOTP> findLatestActiveOTP(@Param("phoneNumber") String phoneNumber, @Param("now") LocalDateTime now);
    
    @Query("SELECT COUNT(a) FROM AuthOTP a WHERE a.phoneNumber = :phoneNumber AND a.createdAt > :startTime AND a.isActive = true")
    long countRecentOTPs(@Param("phoneNumber") String phoneNumber, @Param("startTime") LocalDateTime startTime);
} 