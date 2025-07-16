package com.example.userbehaviormanagement.entities.dto;

import lombok.Data;

@Data
public class OrderDetailDTO {
    private String id;
    private String productName;
    private Integer quantity;
    private Double unitPrice;
}
