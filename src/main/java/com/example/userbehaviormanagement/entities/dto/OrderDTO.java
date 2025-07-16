package com.example.userbehaviormanagement.entities.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderDTO {
    private String id;
    private String totalPrice;
    private String status;
    private String fullName;
    private String createdAt;
    private String updatedAt;
    private List<OrderDetailDTO> orderDetails;
}
