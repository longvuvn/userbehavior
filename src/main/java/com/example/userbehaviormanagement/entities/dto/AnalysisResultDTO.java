package com.example.userbehaviormanagement.entities.dto;

import java.time.Instant;

import lombok.Data;

@Data
public class AnalysisResultDTO {
    private String id;
    private String eventTypeId;
    private String eventTypeName;
    private Integer totalEvents;
    private Integer uniqueSessions;
    private Integer uniqueUsers;
    private Double interactionRate;
    private String mostCommonElement;
    private Integer avgX;
    private Integer avgY;
    private Instant lastUpdated;
}
