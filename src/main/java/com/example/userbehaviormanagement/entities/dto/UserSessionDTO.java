package com.example.userbehaviormanagement.entities.dto;

import lombok.Data;

@Data
public class UserSessionDTO {
    private String id;
    private String ipAddress;
    private String userId;
    private String browserInfo;
    private String osInfo;
    private String createdAt;
}
