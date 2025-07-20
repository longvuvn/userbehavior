package com.example.userbehaviormanagement.entities.dto;


import lombok.Data;

import java.time.Instant;

@Data
public class HeatmapDTO {
    private String id;
    private String pageUrl;
    private Integer x;
    private Integer y;
    private Long count;
    private String eventTypeId;
    private String eventTypeName;
    private Instant lastUpdated;
}
