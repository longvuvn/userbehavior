package com.example.userbehaviormanagement.services;

import com.example.userbehaviormanagement.entities.dto.OrderDTO;
import com.example.userbehaviormanagement.enums.OrderStatus;

import java.util.List;

public interface OrderService {
    List<OrderDTO> getAllOrders();
    OrderDTO getOrderById(String id);
    OrderDTO createOrder(OrderDTO orderDTO);
    OrderDTO updateOrder(String id, OrderStatus status);
    void deleteOrder(String id);
}
