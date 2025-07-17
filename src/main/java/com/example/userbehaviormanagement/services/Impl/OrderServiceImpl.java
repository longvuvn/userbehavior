package com.example.userbehaviormanagement.services.Impl;

import com.example.userbehaviormanagement.entities.Customer;
import com.example.userbehaviormanagement.entities.Order;
import com.example.userbehaviormanagement.entities.dto.OrderDTO;
import com.example.userbehaviormanagement.entities.dto.OrderDetailDTO;
import com.example.userbehaviormanagement.enums.OrderStatus;
import com.example.userbehaviormanagement.repositories.CustomerRepository;
import com.example.userbehaviormanagement.repositories.OrderRepository;
import com.example.userbehaviormanagement.services.OrderDetailService;
import com.example.userbehaviormanagement.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;
    private final CustomerRepository customerRepository;
    private final OrderDetailService orderDetailService;

    @Override
    public List<OrderDTO> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream()
                .map(order -> modelMapper.map(order, OrderDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public OrderDTO getOrderById(String id) {
        UUID orderId = UUID.fromString(id);
        Optional<Order> orderOptional = orderRepository.findById(orderId);
        return orderOptional.map(order -> modelMapper.map(order, OrderDTO.class))
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));
    }

    @Override
    public OrderDTO createOrder(OrderDTO orderDTO) {
        UUID customerId = UUID.fromString(orderDTO.getCustomerId());
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + customerId));
        Order order = modelMapper.map(orderDTO, Order.class);
        order.setCustomer(customer);
        order.setStatus(OrderStatus.PENDING);
        Order savedOrder = orderRepository.save(order);
        if(savedOrder.getId() != null && !orderDTO.getOrderDetails().isEmpty()){
            for (OrderDetailDTO detailDTO : orderDTO.getOrderDetails()) {
                detailDTO.setOrderId(savedOrder.getId().toString());
                orderDetailService.createOrderDetail(detailDTO);
            }

        }
        return modelMapper.map(savedOrder, OrderDTO.class);
    }

    @Override
    public OrderDTO updateOrder(String id, OrderStatus status) {
        UUID orderId = UUID.fromString(id);
        Order existingOrder = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));
        existingOrder.setStatus(status);
        Order updatedOrder = orderRepository.save(existingOrder);
        return modelMapper.map(updatedOrder, OrderDTO.class);
    }

    @Override
    public void deleteOrder(String id) {
        UUID orderId = UUID.fromString(id);
        Order existingOrder = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));
        orderRepository.delete(existingOrder);
    }
}
