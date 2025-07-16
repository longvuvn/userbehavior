package com.example.userbehaviormanagement.entities.dto;


import lombok.Data;

@Data
public class ProductDTO {
    private String id;
    private String name;
    private String categoryName;
    private String price;
    private int totalReviews;
    private double averageRating;
    private String quantity;
    private String createdAt;
    private String updatedAt;
}
