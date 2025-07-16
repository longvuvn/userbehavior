package com.example.userbehaviormanagement.entities.dto;

import lombok.Data;

@Data
public class UserDTO {
    private String id;
    private String fullName;
    private String email;
    private String password;
    private String roleName;
    private String status;
    private String createdAt;
    private String updatedAt;
}
