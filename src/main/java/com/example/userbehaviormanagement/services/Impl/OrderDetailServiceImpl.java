package com.example.userbehaviormanagement.services.Impl;

import com.example.userbehaviormanagement.entities.dto.OrderDetailDTO;
import com.example.userbehaviormanagement.services.OrderDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderDetailServiceImpl implements OrderDetailService {
    @Override
    public List<OrderDetailDTO> getAllOrderDetails() {
        return List.of();
    }

    @Override
    public OrderDetailDTO getOrderDetailById(String id) {
        return null;
    }

    @Override
    public OrderDetailDTO createOrderDetail(OrderDetailDTO orderDetailDTO) {
        return null;
    }

    @Override
    public OrderDetailDTO updateOrderDetail(String id, OrderDetailDTO orderDetailDTO) {
        return null;
    }

    @Override
    public void deleteOrderDetail(String id) {

    }
}
