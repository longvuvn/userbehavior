package com.example.userbehaviormanagement.entities.dto;

import lombok.Data;

@Data
public class ReviewDTO {
    private String id;
    private String comment;
    private String rating;
    private String customerId;
    private String productId;
    private String productName;
    private String customerName;
    private String createdAt;
    private String updatedAt;
    private String status;
}
