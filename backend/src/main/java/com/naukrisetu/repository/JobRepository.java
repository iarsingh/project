package com.naukrisetu.repository;

import com.naukrisetu.model.Job;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
    
    Page<Job> findByDistrictIdAndIsActiveTrue(Long districtId, Pageable pageable);
    
    Page<Job> findByQualificationRequiredContainingIgnoreCaseAndIsActiveTrue(String qualification, Pageable pageable);
    
    Page<Job> findByOrganizationContainingIgnoreCaseAndIsActiveTrue(String organization, Pageable pageable);
    
    @Query("SELECT j FROM Job j WHERE j.districtId IN :districtIds AND j.isActive = true")
    Page<Job> findByDistrictIdsAndIsActiveTrue(@Param("districtIds") List<Long> districtIds, Pageable pageable);
    
    @Query("SELECT j FROM Job j WHERE j.applicationDeadline > :now AND j.isActive = true")
    Page<Job> findActiveJobsWithDeadline(@Param("now") LocalDateTime now, Pageable pageable);
    
    @Query("SELECT j FROM Job j WHERE j.districtId = :districtId AND j.qualificationRequired LIKE %:qualification% AND j.isActive = true")
    Page<Job> findByDistrictAndQualification(@Param("districtId") Long districtId, @Param("qualification") String qualification, Pageable pageable);

    // Statistics methods
    long countByIsActiveTrue();
    
    @Query("SELECT j.districtId as districtId, COUNT(j) as count FROM Job j WHERE j.isActive = true GROUP BY j.districtId")
    List<Map<String, Object>> countByDistrictIdAndIsActiveTrue();
    
    long countByCreatedAtAfter(LocalDateTime startDate);
    
    @Query("SELECT j.jobType as type, COUNT(j) as count FROM Job j GROUP BY j.jobType")
    List<Map<String, Object>> countByJobType();
    
    @Query("SELECT j.qualificationRequired as qualification, COUNT(j) as count FROM Job j GROUP BY j.qualificationRequired")
    List<Map<String, Object>> countByQualificationRequired();
    
    long countByDistrictIdAndCreatedAtAfter(Long districtId, LocalDateTime startDate);
} 