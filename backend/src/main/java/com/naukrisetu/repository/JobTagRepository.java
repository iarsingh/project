package com.naukrisetu.repository;

import com.naukrisetu.model.JobTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface JobTagRepository extends JpaRepository<JobTag, Long> {
    
    Optional<JobTag> findByName(String name);
    
    Set<JobTag> findByNameIn(Set<String> names);
    
    boolean existsByName(String name);
} 