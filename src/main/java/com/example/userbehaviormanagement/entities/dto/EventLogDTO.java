package com.example.userbehaviormanagement.entities.dto;

import lombok.Data;

@Data
public class EventLogDTO {
    private String id;
    private String userSessionId;
    private String typeId;
    private String url;
    private Integer x;
    private Integer y;
    private String eventData;
    private String timestamp;
}
