package com.naukrisetu.controller;

import com.naukrisetu.model.Referral;
import com.naukrisetu.model.User;
import com.naukrisetu.model.Job;
import com.naukrisetu.repository.JobRepository;
import com.naukrisetu.repository.ReferralRepository;
import com.naukrisetu.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/referrals")
public class ReferralController {

    @Autowired
    private ReferralRepository referralRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JobRepository jobRepository;

    @PostMapping("/jobs/{jobId}/refer")
    public ResponseEntity<?> createReferral(
            @PathVariable Long jobId,
            @RequestParam Long referrerId,
            @RequestParam String referredEmail,
            @RequestParam String referredPhone) {
        
        User referrer = userRepository.findById(referrerId)
                .orElseThrow(() -> new RuntimeException("Referrer not found"));
        
        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found"));

        Referral referral = new Referral();
        referral.setReferrer(referrer);
        referral.setJob(job);
        referral.setReferredEmail(referredEmail);
        referral.setReferredPhone(referredPhone);
        referral.setStatus(Referral.ReferralStatus.PENDING);

        return ResponseEntity.ok(referralRepository.save(referral));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Page<Referral>> getUserReferrals(
            @PathVariable Long userId,
            Pageable pageable) {
        return ResponseEntity.ok(referralRepository.findByReferrerId(userId, pageable));
    }

    @GetMapping("/job/{jobId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Page<Referral>> getJobReferrals(
            @PathVariable Long jobId,
            Pageable pageable) {
        return ResponseEntity.ok(referralRepository.findByJobId(jobId, pageable));
    }

    @PutMapping("/{id}/status")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateReferralStatus(
            @PathVariable Long id,
            @RequestParam Referral.ReferralStatus status) {
        
        Referral referral = referralRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Referral not found"));
        
        referral.setStatus(status);
        return ResponseEntity.ok(referralRepository.save(referral));
    }

    @GetMapping("/stats/user/{userId}")
    public ResponseEntity<?> getUserReferralStats(@PathVariable Long userId) {
        long totalReferrals = referralRepository.countByReferrerId(userId);
        long acceptedReferrals = referralRepository.countByReferrerIdAndStatus(userId, Referral.ReferralStatus.ACCEPTED);
        long hiredReferrals = referralRepository.countByReferrerIdAndStatus(userId, Referral.ReferralStatus.HIRED);
        
        return ResponseEntity.ok(new ReferralStats(totalReferrals, acceptedReferrals, hiredReferrals));
    }

    private static class ReferralStats {
        private final long totalReferrals;
        private final long acceptedReferrals;
        private final long hiredReferrals;

        public ReferralStats(long totalReferrals, long acceptedReferrals, long hiredReferrals) {
            this.totalReferrals = totalReferrals;
            this.acceptedReferrals = acceptedReferrals;
            this.hiredReferrals = hiredReferrals;
        }

        public long getTotalReferrals() {
            return totalReferrals;
        }

        public long getAcceptedReferrals() {
            return acceptedReferrals;
        }

        public long getHiredReferrals() {
            return hiredReferrals;
        }
    }
} 