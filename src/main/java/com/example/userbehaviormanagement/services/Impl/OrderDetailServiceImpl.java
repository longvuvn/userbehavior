package com.example.userbehaviormanagement.services.Impl;

import com.example.userbehaviormanagement.entities.Order;
import com.example.userbehaviormanagement.entities.OrderDetail;
import com.example.userbehaviormanagement.entities.Product;
import com.example.userbehaviormanagement.entities.dto.OrderDetailDTO;
import com.example.userbehaviormanagement.repositories.OrderDetailRepository;
import com.example.userbehaviormanagement.repositories.OrderRepository;
import com.example.userbehaviormanagement.repositories.ProductRepository;
import com.example.userbehaviormanagement.services.OrderDetailService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderDetailServiceImpl implements OrderDetailService {

    private final OrderDetailRepository orderDetailRepository;
    private final ModelMapper modelMapper;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    @Override
    public List<OrderDetailDTO> getAllOrderDetails() {
        List<OrderDetail> orderDetails = orderDetailRepository.findAll();
        return orderDetails.stream()
                .map(orderDetail -> modelMapper.map(orderDetail, OrderDetailDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public OrderDetailDTO getOrderDetailById(String id) {
        UUID orderId = UUID.fromString(id);
        Optional<OrderDetail> orderDetailOptional = orderDetailRepository.findById(orderId);
        return orderDetailOptional.map(orderDetail -> modelMapper.map(orderDetail, OrderDetailDTO.class))
                .orElseThrow(() -> new RuntimeException("Order detail not found with id: " + id));
    }

    @Override
    public OrderDetailDTO createOrderDetail(OrderDetailDTO orderDetailDTO) {
        UUID orderId = UUID.fromString(orderDetailDTO.getOrderId());
        UUID productId = UUID.fromString(orderDetailDTO.getProductId());

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + orderId));
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + productId));

        OrderDetail orderDetail = modelMapper.map(orderDetailDTO, OrderDetail.class);
        orderDetail.setOrder(order);
        orderDetail.setProduct(product);
        BigDecimal unitPrice = product.getPrice();
        orderDetail.setUnitPrice(unitPrice);
        BigDecimal currentTotal = order.getTotalPrice() != null ? order.getTotalPrice() : BigDecimal.ZERO;
        BigDecimal lineTotal = unitPrice.multiply(BigDecimal.valueOf(orderDetailDTO.getQuantity()));
        order.setTotalPrice(currentTotal.add(lineTotal));
        orderRepository.save(order);
        OrderDetail savedOrderDetail = orderDetailRepository.save(orderDetail);
        return modelMapper.map(savedOrderDetail, OrderDetailDTO.class);
    }

    @Override
    public OrderDetailDTO updateOrderDetail(String id, OrderDetailDTO orderDetailDTO) {
        UUID detailId = UUID.fromString(id);
        OrderDetail existingDetail = orderDetailRepository.findById(detailId)
                .orElseThrow(() -> new RuntimeException("OrderDetail not found with id: " + id));
        Order order = existingDetail.getOrder();
        BigDecimal oldLineTotal = existingDetail.getUnitPrice()
                .multiply(BigDecimal.valueOf(existingDetail.getQuantity()));
        BigDecimal currentTotal = order.getTotalPrice() != null ? order.getTotalPrice() : BigDecimal.ZERO;
        order.setTotalPrice(currentTotal.subtract(oldLineTotal));
        Integer newQuantity = orderDetailDTO.getQuantity();
        if (newQuantity == null || newQuantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than 0");
        }
        existingDetail.setQuantity(newQuantity);
        BigDecimal newLineTotal = existingDetail.getUnitPrice().multiply(BigDecimal.valueOf(newQuantity));
        order.setTotalPrice(order.getTotalPrice().add(newLineTotal));
        orderRepository.save(order);
        OrderDetail updatedDetail = orderDetailRepository.save(existingDetail);
        return modelMapper.map(updatedDetail, OrderDetailDTO.class);
    }

    @Override
    public void deleteOrderDetail(String id) {
        UUID orderId = UUID.fromString(id);
        OrderDetail existingOrderDetail = orderDetailRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order detail not found with id: " + id));
        orderDetailRepository.delete(existingOrderDetail);
    }
}
