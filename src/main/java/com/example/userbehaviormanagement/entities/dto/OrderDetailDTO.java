package com.example.userbehaviormanagement.entities.dto;

import lombok.Data;

@Data
public class OrderDetailDTO {
    private String id;
    private String orderId;
    private String productId;
    private String productName;
    private Integer quantity;
    private Double unitPrice;
    private Double totalPrice;
}
