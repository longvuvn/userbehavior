package com.example.userbehaviormanagement.entities.dto;

import lombok.Data;

@Data
public class AdminDTO {
    private String id;
    private String fullName;
    private String email;
    private String password;
    private String department;
    private String roleName;
    private String status;
    private String createdAt;
    private String updatedAt;
}
