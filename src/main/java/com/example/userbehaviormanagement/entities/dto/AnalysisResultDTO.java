package com.example.userbehaviormanagement.entities.dto;

import java.time.Instant;

import lombok.Data;

@Data
public class AnalysisResultDTO {
    private String id;
    private String eventTypeId;
    private String eventTypeName;
    private Integer totalCount;
    private Integer avgX;
    private Integer avgY;
    private String mostCommonElement;
    private Instant lastUpdated;
}
