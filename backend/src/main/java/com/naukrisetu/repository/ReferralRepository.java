package com.naukrisetu.repository;

import com.naukrisetu.model.Referral;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface ReferralRepository extends JpaRepository<Referral, Long> {
    
    Page<Referral> findByReferrerId(Long referrerId, Pageable pageable);
    
    Page<Referral> findByJobId(Long jobId, Pageable pageable);
    
    @Query("SELECT r FROM Referral r WHERE r.referrer.id = :referrerId AND r.status = :status")
    Page<Referral> findByReferrerIdAndStatus(@Param("referrerId") Long referrerId, @Param("status") Referral.ReferralStatus status, Pageable pageable);
    
    @Query("SELECT SUM(r.rewardPoints) FROM Referral r WHERE r.referrer.id = :referrerId")
    Integer calculateTotalRewardPoints(@Param("referrerId") Long referrerId);

    // Statistics methods
    long countByStatus(Referral.ReferralStatus status);
    
    @Query("SELECT SUM(r.rewardPoints) FROM Referral r")
    Integer calculateTotalRewardPoints();
    
    long countByReferredAtAfter(LocalDateTime startDate);

    long countByReferrerId(Long referrerId);
    
    long countByReferrerIdAndStatus(Long referrerId, Referral.ReferralStatus status);
} 