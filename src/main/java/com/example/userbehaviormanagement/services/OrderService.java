package com.example.userbehaviormanagement.services;

import com.example.userbehaviormanagement.entities.dto.OrderDTO;

import java.util.List;

public interface OrderService {
    List<OrderDTO> getAllOrders();
    OrderDTO getOrderById(String id);
    OrderDTO createOrder(OrderDTO orderDTO);
    OrderDTO updateOrder(String id, OrderDTO orderDTO);
    void deleteOrder(String id);
}
