package com.example.userbehaviormanagement.services.Impl;

import com.example.userbehaviormanagement.entities.dto.OrderDTO;
import com.example.userbehaviormanagement.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    @Override
    public List<OrderDTO> getAllOrders() {
        return List.of();
    }

    @Override
    public OrderDTO getOrderById(String id) {
        return null;
    }

    @Override
    public OrderDTO createOrder(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO updateOrder(String id, OrderDTO orderDTO) {
        return null;
    }

    @Override
    public void deleteOrder(String id) {

    }
}
