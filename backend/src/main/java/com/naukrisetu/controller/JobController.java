package com.naukrisetu.controller;

import com.naukrisetu.model.Job;
import com.naukrisetu.repository.JobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/jobs")
@RequiredArgsConstructor
public class JobController {

    private final JobRepository jobRepository;

    @GetMapping("/search")
    public ResponseEntity<Page<Job>> searchJobs(
            @RequestParam(required = false) Long districtId,
            @RequestParam(required = false) String qualification,
            @RequestParam(required = false) String organization,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "createdAt") String sortBy,
            @RequestParam(defaultValue = "desc") String direction) {

        Sort.Direction sortDirection = Sort.Direction.fromString(direction.toUpperCase());
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, sortBy));

        Page<Job> jobs;
        if (districtId != null && qualification != null) {
            jobs = jobRepository.findByDistrictAndQualification(districtId, qualification, pageable);
        } else if (districtId != null) {
            jobs = jobRepository.findByDistrictIdAndIsActiveTrue(districtId, pageable);
        } else if (qualification != null) {
            jobs = jobRepository.findByQualificationRequiredContainingIgnoreCaseAndIsActiveTrue(qualification, pageable);
        } else if (organization != null) {
            jobs = jobRepository.findByOrganizationContainingIgnoreCaseAndIsActiveTrue(organization, pageable);
        } else {
            jobs = jobRepository.findActiveJobsWithDeadline(LocalDateTime.now(), pageable);
        }

        return ResponseEntity.ok(jobs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable Long id) {
        return jobRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Job> createJob(@RequestBody Job job) {
        job.setCreatedAt(LocalDateTime.now());
        job.setUpdatedAt(LocalDateTime.now());
        job.setActive(true);
        return ResponseEntity.ok(jobRepository.save(job));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Job> updateJob(@PathVariable Long id, @RequestBody Job jobDetails) {
        return jobRepository.findById(id)
                .map(job -> {
                    job.setTitle(jobDetails.getTitle());
                    job.setDescription(jobDetails.getDescription());
                    job.setDepartment(jobDetails.getDepartment());
                    job.setOrganization(jobDetails.getOrganization());
                    job.setDistrictId(jobDetails.getDistrictId());
                    job.setQualificationRequired(jobDetails.getQualificationRequired());
                    job.setExperienceRequired(jobDetails.getExperienceRequired());
                    job.setSalaryRange(jobDetails.getSalaryRange());
                    job.setApplicationDeadline(jobDetails.getApplicationDeadline());
                    job.setJobType(jobDetails.getJobType());
                    job.setVacancyCount(jobDetails.getVacancyCount());
                    job.setUpdatedAt(LocalDateTime.now());
                    return ResponseEntity.ok(jobRepository.save(job));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteJob(@PathVariable Long id) {
        return jobRepository.findById(id)
                .map(job -> {
                    job.setActive(false);
                    job.setUpdatedAt(LocalDateTime.now());
                    jobRepository.save(job);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/nearby")
    public ResponseEntity<Page<Job>> getNearbyJobs(
            @RequestParam List<Long> districtIds,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
        Page<Job> jobs = jobRepository.findByDistrictIdsAndIsActiveTrue(districtIds, pageable);
        return ResponseEntity.ok(jobs);
    }
} 