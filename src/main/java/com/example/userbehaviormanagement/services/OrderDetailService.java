package com.example.userbehaviormanagement.services;

import com.example.userbehaviormanagement.entities.dto.OrderDetailDTO;

import java.util.List;

public interface OrderDetailService {
    List<OrderDetailDTO> getAllOrderDetails();
    OrderDetailDTO getOrderDetailById(String id);
    OrderDetailDTO createOrderDetail(OrderDetailDTO orderDetailDTO);
    OrderDetailDTO updateOrderDetail(String id, OrderDetailDTO orderDetailDTO);
    void deleteOrderDetail(String id);
}
