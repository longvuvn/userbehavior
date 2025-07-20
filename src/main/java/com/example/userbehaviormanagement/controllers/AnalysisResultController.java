package com.example.userbehaviormanagement.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.userbehaviormanagement.entities.dto.AnalysisResultDTO;
import com.example.userbehaviormanagement.services.AnalysisResultService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/analysis-results")
@RequiredArgsConstructor
public class AnalysisResultController {

    private final AnalysisResultService analysisResultService;

    @GetMapping
    public ResponseEntity<List<AnalysisResultDTO>> getAll() {
        List<AnalysisResultDTO> results = analysisResultService.getAllAnalysisResults();
        return ResponseEntity.status(200).body(results);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnalysisResultDTO> getById(@PathVariable String id) {
        AnalysisResultDTO result = analysisResultService.getAnalysisResultById(id);
        return ResponseEntity.status(200).body(result);
    }

    @PostMapping
    public ResponseEntity<AnalysisResultDTO> create(@RequestBody AnalysisResultDTO analysisResultDTO) {
        AnalysisResultDTO createdResult = analysisResultService.createAnalysisResult(analysisResultDTO);
        return ResponseEntity.status(201).body(createdResult);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AnalysisResultDTO> update(@PathVariable String id, AnalysisResultDTO analysisResultDTO) {
        AnalysisResultDTO updatedResult = analysisResultService.updateAnalysisResult(id, analysisResultDTO);
        return ResponseEntity.status(200).body(updatedResult);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        analysisResultService.deleteAnalysisResult(id);
        return ResponseEntity.status(204).build();
    }
}
