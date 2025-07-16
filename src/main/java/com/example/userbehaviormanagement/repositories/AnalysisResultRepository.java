package com.example.userbehaviormanagement.repositories;

import com.example.userbehaviormanagement.entities.AnalysisResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AnalysisResultRepository extends JpaRepository<AnalysisResult, UUID> {
}
