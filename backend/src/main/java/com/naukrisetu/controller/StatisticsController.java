package com.naukrisetu.controller;

import com.naukrisetu.model.Referral;
import com.naukrisetu.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/statistics")
@RequiredArgsConstructor
public class StatisticsController {

    private final JobRepository jobRepository;
    private final JobApplicationRepository jobApplicationRepository;
    private final UserRepository userRepository;
    private final DocumentRepository documentRepository;
    private final ReferralRepository referralRepository;
    private final DistrictRepository districtRepository;

    @GetMapping("/dashboard")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Map<String, Object>> getDashboardStatistics() {
        Map<String, Object> statistics = new HashMap<>();
        
        // Job statistics
        statistics.put("totalJobs", jobRepository.count());
        statistics.put("activeJobs", jobRepository.countByIsActiveTrue());
        statistics.put("jobsByDistrict", jobRepository.countByDistrictIdAndIsActiveTrue());
        
        // Application statistics
        statistics.put("totalApplications", jobApplicationRepository.count());
        statistics.put("applicationsByStatus", jobApplicationRepository.countByStatus());
        statistics.put("offlineApplications", jobApplicationRepository.countByIsOfflineSubmissionTrue());
        
        // User statistics
        statistics.put("totalUsers", userRepository.count());
        statistics.put("activeUsers", userRepository.countByIsActiveTrue());
        statistics.put("verifiedUsers", userRepository.countByIsVerifiedTrue());
        
        // Document statistics
        statistics.put("totalDocuments", documentRepository.count());
        statistics.put("verifiedDocuments", documentRepository.countByIsVerifiedTrue());
        statistics.put("pendingVerification", documentRepository.countByIsVerifiedFalse());
        
        // Referral statistics
        statistics.put("totalReferrals", referralRepository.count());
        statistics.put("successfulReferrals", referralRepository.countByStatus(Referral.ReferralStatus.HIRED));
        statistics.put("totalRewardPoints", referralRepository.calculateTotalRewardPoints());
        
        // District statistics
        statistics.put("totalDistricts", districtRepository.count());
        statistics.put("activeDistricts", districtRepository.countByIsActiveTrue());
        statistics.put("districtsByState", districtRepository.countByState());
        
        return ResponseEntity.ok(statistics);
    }

    @GetMapping("/jobs/trends")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Map<String, Object>> getJobTrends(
            @RequestParam(required = false) Long districtId,
            @RequestParam(required = false) String timeRange) {
        
        Map<String, Object> trends = new HashMap<>();
        LocalDateTime startDate = calculateStartDate(timeRange);
        
        trends.put("newJobs", jobRepository.countByCreatedAtAfter(startDate));
        trends.put("applications", jobApplicationRepository.countByApplicationDateAfter(startDate));
        trends.put("jobTypes", jobRepository.countByJobType());
        trends.put("qualifications", jobRepository.countByQualificationRequired());
        
        if (districtId != null) {
            trends.put("districtJobs", jobRepository.countByDistrictIdAndCreatedAtAfter(districtId, startDate));
            trends.put("districtApplications", jobApplicationRepository.countByJobDistrictIdAndApplicationDateAfter(districtId, startDate));
        }
        
        return ResponseEntity.ok(trends);
    }

    @GetMapping("/users/activity")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Map<String, Object>> getUserActivity(
            @RequestParam(required = false) String timeRange) {
        
        Map<String, Object> activity = new HashMap<>();
        LocalDateTime startDate = calculateStartDate(timeRange);
        
        activity.put("newUsers", userRepository.countByCreatedAtAfter(startDate));
        activity.put("activeUsers", userRepository.countByLastLoginAfter(startDate));
        activity.put("documentUploads", documentRepository.countByCreatedAtAfter(startDate));
        activity.put("referrals", referralRepository.countByCreatedAtAfter(startDate));
        
        return ResponseEntity.ok(activity);
    }

    private LocalDateTime calculateStartDate(String timeRange) {
        LocalDateTime now = LocalDateTime.now();
        return switch (timeRange != null ? timeRange.toLowerCase() : "month") {
            case "week" -> now.minusWeeks(1);
            case "month" -> now.minusMonths(1);
            case "quarter" -> now.minusMonths(3);
            case "year" -> now.minusYears(1);
            default -> now.minusMonths(1);
        };
    }
} 