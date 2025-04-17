package com.naukrisetu.controller;

import com.naukrisetu.model.District;
import com.naukrisetu.repository.DistrictRepository;
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
import java.util.Map;

@RestController
@RequestMapping("/api/districts")
@RequiredArgsConstructor
public class DistrictController {

    private final DistrictRepository districtRepository;

    @GetMapping
    public ResponseEntity<Page<District>> getAllDistricts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String state) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "name"));
        
        if (state != null && !state.isEmpty()) {
            return ResponseEntity.ok(districtRepository.findByState(state, pageable));
        }
        return ResponseEntity.ok(districtRepository.findByIsActiveTrue(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<District> getDistrictById(@PathVariable Long id) {
        return districtRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/states")
    public ResponseEntity<List<String>> getAllStates() {
        return ResponseEntity.ok(districtRepository.findDistinctStates());
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<District> createDistrict(@RequestBody District district) {
        LocalDateTime now = LocalDateTime.now();
        district.setActive(true);
        district.setCreatedAt(now);
        district.setUpdatedAt(now);
        return ResponseEntity.ok(districtRepository.save(district));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<District> updateDistrict(
            @PathVariable Long id,
            @RequestBody District districtDetails) {
        return districtRepository.findById(id)
                .map(district -> {
                    district.setName(districtDetails.getName());
                    district.setState(districtDetails.getState());
                    district.setLatitude(districtDetails.getLatitude());
                    district.setLongitude(districtDetails.getLongitude());
                    district.setPopulation(districtDetails.getPopulation());
                    district.setLiteracyRate(districtDetails.getLiteracyRate());
                    district.setEmploymentRate(districtDetails.getEmploymentRate());
                    district.setUpdatedAt(LocalDateTime.now());
                    return ResponseEntity.ok(districtRepository.save(district));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteDistrict(@PathVariable Long id) {
        return districtRepository.findById(id)
                .map(district -> {
                    district.setActive(false);
                    district.setUpdatedAt(LocalDateTime.now());
                    districtRepository.save(district);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/stats")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Map<String, Object>> getDistrictStats() {
        return ResponseEntity.ok(Map.of(
            "totalDistricts", districtRepository.count(),
            "activeDistricts", districtRepository.countByIsActiveTrue(),
            "stateCount", (long) districtRepository.findDistinctStates().size(),
            "stateWiseCount", districtRepository.countByState()
        ));
    }
} 