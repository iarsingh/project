package com.naukrisetu.controller;

import com.naukrisetu.model.JobApplication;
import com.naukrisetu.repository.JobApplicationRepository;
import com.naukrisetu.repository.JobRepository;
import com.naukrisetu.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/applications")
@RequiredArgsConstructor
public class JobApplicationController {

    private final JobApplicationRepository applicationRepository;
    private final JobRepository jobRepository;
    private final UserRepository userRepository;

    @PostMapping("/jobs/{jobId}/apply")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> applyForJob(
            @PathVariable Long jobId,
            @RequestParam Long userId,
            @RequestParam(required = false) String notes) {

        if (!jobRepository.existsById(jobId)) {
            return ResponseEntity.badRequest().body("Job not found");
        }

        if (!userRepository.existsById(userId)) {
            return ResponseEntity.badRequest().body("User not found");
        }

        if (applicationRepository.findByUserIdAndJobId(userId, jobId).isPresent()) {
            return ResponseEntity.badRequest().body("You have already applied for this job");
        }

        JobApplication application = new JobApplication();
        application.setUser(userRepository.findById(userId).get());
        application.setJob(jobRepository.findById(jobId).get());
        application.setApplicationDate(LocalDateTime.now());
        application.setLastStatusUpdate(LocalDateTime.now());
        application.setApplicationNotes(notes);
        application.setStatus(JobApplication.ApplicationStatus.SUBMITTED);

        return ResponseEntity.ok(applicationRepository.save(application));
    }

    @GetMapping("/user/{userId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Page<JobApplication>> getUserApplications(
            @PathVariable Long userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "applicationDate"));
        Page<JobApplication> applications = applicationRepository.findByUserId(userId, pageable);
        return ResponseEntity.ok(applications);
    }

    @GetMapping("/job/{jobId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Page<JobApplication>> getJobApplications(
            @PathVariable Long jobId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "applicationDate"));
        Page<JobApplication> applications = applicationRepository.findByJobId(jobId, pageable);
        return ResponseEntity.ok(applications);
    }

    @PutMapping("/{id}/status")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateApplicationStatus(
            @PathVariable Long id,
            @RequestParam JobApplication.ApplicationStatus status,
            @RequestParam(required = false) String notes) {

        return applicationRepository.findById(id)
                .map(application -> {
                    application.setStatus(status);
                    application.setLastStatusUpdate(LocalDateTime.now());
                    if (notes != null) {
                        application.setApplicationNotes(notes);
                    }
                    return ResponseEntity.ok(applicationRepository.save(application));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/offline-submit")
    public ResponseEntity<?> submitOfflineApplication(
            @RequestParam Long userId,
            @RequestParam Long jobId,
            @RequestParam String referenceId) {

        if (!jobRepository.existsById(jobId)) {
            return ResponseEntity.badRequest().body("Job not found");
        }

        if (!userRepository.existsById(userId)) {
            return ResponseEntity.badRequest().body("User not found");
        }

        JobApplication application = new JobApplication();
        application.setUser(userRepository.findById(userId).get());
        application.setJob(jobRepository.findById(jobId).get());
        application.setApplicationDate(LocalDateTime.now());
        application.setLastStatusUpdate(LocalDateTime.now());
        application.setOfflineSubmission(true);
        application.setOfflineReferenceId(referenceId);
        application.setStatus(JobApplication.ApplicationStatus.SUBMITTED);

        return ResponseEntity.ok(applicationRepository.save(application));
    }
} 