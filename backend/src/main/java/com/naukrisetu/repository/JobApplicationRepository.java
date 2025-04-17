package com.naukrisetu.repository;

import com.naukrisetu.model.JobApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface JobApplicationRepository extends JpaRepository<JobApplication, Long> {
    
    Page<JobApplication> findByUserId(Long userId, Pageable pageable);
    
    Page<JobApplication> findByJobId(Long jobId, Pageable pageable);
    
    Optional<JobApplication> findByUserIdAndJobId(Long userId, Long jobId);
    
    @Query("SELECT ja FROM JobApplication ja WHERE ja.user.id = :userId AND ja.status = :status")
    Page<JobApplication> findByUserIdAndStatus(@Param("userId") Long userId, @Param("status") JobApplication.ApplicationStatus status, Pageable pageable);
    
    @Query("SELECT ja FROM JobApplication ja WHERE ja.job.id = :jobId AND ja.status = :status")
    Page<JobApplication> findByJobIdAndStatus(@Param("jobId") Long jobId, @Param("status") JobApplication.ApplicationStatus status, Pageable pageable);
    
    List<JobApplication> findByIsOfflineSubmissionTrue();

    // Statistics methods
    @Query("SELECT ja.status as status, COUNT(ja) as count FROM JobApplication ja GROUP BY ja.status")
    List<Map<String, Object>> countByStatus();
    
    long countByIsOfflineSubmissionTrue();
    
    long countByApplicationDateAfter(LocalDateTime startDate);
    
    @Query("SELECT COUNT(ja) FROM JobApplication ja WHERE ja.job.districtId = :districtId AND ja.applicationDate > :startDate")
    long countByJobDistrictIdAndApplicationDateAfter(@Param("districtId") Long districtId, @Param("startDate") LocalDateTime startDate);
} 