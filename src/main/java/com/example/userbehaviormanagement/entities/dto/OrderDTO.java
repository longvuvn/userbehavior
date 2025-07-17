package com.example.userbehaviormanagement.entities.dto;

import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
public class OrderDTO {
    private String id;
    private Double totalPrice;
    private String status;
    private String customerId;
    private String customerName;
    private Instant createdAt;
    private Instant updatedAt;
    private List<OrderDetailDTO> orderDetails;
}
