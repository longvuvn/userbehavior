package com.example.userbehaviormanagement.services;

import java.util.List;

import com.example.userbehaviormanagement.entities.dto.AnalysisResultDTO;

public interface AnalysisResultService {
    List<AnalysisResultDTO> getAllAnalysisResults();

    AnalysisResultDTO getAnalysisResultById(String id);

    AnalysisResultDTO createAnalysisResult(AnalysisResultDTO analysisResultDTO);

    AnalysisResultDTO updateAnalysisResult(String id, AnalysisResultDTO analysisResultDTO);

    void deleteAnalysisResult(String id);
}
