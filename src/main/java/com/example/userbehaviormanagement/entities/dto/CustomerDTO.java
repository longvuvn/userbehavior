package com.example.userbehaviormanagement.entities.dto;

import lombok.Data;

@Data
public class CustomerDTO {
    private String id;
    private String fullName;
    private String email;
    private String password;
    private String phoneNumber;
    private String roleName;
    private String status;
    private String avatar;
    private String createdAt;
    private String updatedAt;
}
