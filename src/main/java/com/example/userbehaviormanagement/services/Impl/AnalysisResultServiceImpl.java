package com.example.userbehaviormanagement.services.Impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import com.example.userbehaviormanagement.entities.AnalysisResult;
import com.example.userbehaviormanagement.entities.EventType;
import com.example.userbehaviormanagement.entities.dto.AnalysisResultDTO;
import com.example.userbehaviormanagement.repositories.AnalysisResultRepository;
import com.example.userbehaviormanagement.repositories.EventTypeRepository;
import com.example.userbehaviormanagement.services.AnalysisResultService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AnalysisResultServiceImpl implements AnalysisResultService {

    private final AnalysisResultRepository analysisResultRepository;
    private final ModelMapper modelMapper;
    private final EventTypeRepository eventTypeRepository;

    @Override
    public List<AnalysisResultDTO> getAllAnalysisResults() {
        List<AnalysisResult> analysisResults = analysisResultRepository.findAll();
        return analysisResults.stream()
                .map(analysisResult -> modelMapper.map(analysisResult, AnalysisResultDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public AnalysisResultDTO getAnalysisResultById(String id) {
        UUID analysisResultId = UUID.fromString(id);
        Optional<AnalysisResult> analysisResultOptional = analysisResultRepository.findById(analysisResultId);
        return analysisResultOptional.map(analysisResult -> modelMapper.map(analysisResult, AnalysisResultDTO.class))
                .orElseThrow(() -> new RuntimeException("Analysis result not found with id: " + id));
    }

    @Override
    public AnalysisResultDTO createAnalysisResult(AnalysisResultDTO analysisResultDTO) {
        if (analysisResultDTO.getEventTypeId() == null || analysisResultDTO.getEventTypeId().isBlank()) {
            throw new RuntimeException("eventTypeId is required and cannot be null or blank");
        }
        AnalysisResult analysisResult = modelMapper.map(analysisResultDTO, AnalysisResult.class);
        // PHẢI SET eventType CHO analysisResult
        EventType eventType = eventTypeRepository.findById(UUID.fromString(analysisResultDTO.getEventTypeId()))
                .orElseThrow(() -> new RuntimeException("EventType not found"));
        analysisResult.setEventType(eventType);

        AnalysisResult savedAnalysisResult = analysisResultRepository.save(analysisResult);
        return modelMapper.map(savedAnalysisResult, AnalysisResultDTO.class);
    }

    @Override
    public AnalysisResultDTO updateAnalysisResult(String id, AnalysisResultDTO analysisResultDTO) {
        UUID analysisResultId = UUID.fromString(id);
        AnalysisResult existingAnalysisResult = analysisResultRepository.findById(analysisResultId)
                .orElseThrow(() -> new RuntimeException("Analysis result not found with id: " + id));
        modelMapper.map(analysisResultDTO, existingAnalysisResult);
        AnalysisResult updatedAnalysisResult = analysisResultRepository.save(existingAnalysisResult);
        return modelMapper.map(updatedAnalysisResult, AnalysisResultDTO.class);
    }

    @Override
    public void deleteAnalysisResult(String id) {
        UUID analysisResultId = UUID.fromString(id);
        if (!analysisResultRepository.existsById(analysisResultId)) {
            throw new RuntimeException("Analysis result not found with id: " + id);
        }
        analysisResultRepository.deleteById(analysisResultId);
    }

}
