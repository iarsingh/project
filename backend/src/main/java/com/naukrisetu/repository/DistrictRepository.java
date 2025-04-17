package com.naukrisetu.repository;

import com.naukrisetu.model.District;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface DistrictRepository extends JpaRepository<District, Long> {
    
    Page<District> findByState(String state, Pageable pageable);
    
    Page<District> findByIsActiveTrue(Pageable pageable);
    
    @Query("SELECT d FROM District d WHERE d.state = :state AND d.isActive = true")
    List<District> findActiveDistrictsByState(@Param("state") String state);
    
    @Query("SELECT DISTINCT d.state FROM District d WHERE d.isActive = true")
    List<String> findDistinctStates();

    // Statistics methods
    long countByIsActiveTrue();
    
    @Query("SELECT d.state as state, COUNT(d) as count FROM District d WHERE d.isActive = true GROUP BY d.state")
    List<Map<String, Object>> countByState();
} 