package com.example.userbehaviormanagement.controllers;


import com.example.userbehaviormanagement.entities.dto.OrderDetailDTO;
import com.example.userbehaviormanagement.services.OrderDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order-details")
@RequiredArgsConstructor
public class OrderDetailController {

    private final OrderDetailService orderDetailService;

    @GetMapping
    public ResponseEntity<List<OrderDetailDTO>> getAll(){
        List<OrderDetailDTO> orderDetails = orderDetailService.getAllOrderDetails();
        return ResponseEntity.status(HttpStatus.OK).body(orderDetails);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDetailDTO> getById(@PathVariable String id){
        OrderDetailDTO orderDetail = orderDetailService.getOrderDetailById(id);
        return ResponseEntity.status(HttpStatus.OK).body(orderDetail);
    }

    @PostMapping
    public ResponseEntity<OrderDetailDTO> create (@RequestBody OrderDetailDTO orderDetail){
        OrderDetailDTO createdOrderDetail = orderDetailService.createOrderDetail(orderDetail);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrderDetail);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderDetailDTO> update(@PathVariable String id, @RequestBody OrderDetailDTO orderDetail){
        OrderDetailDTO updatedOrderDetail = orderDetailService.updateOrderDetail(id, orderDetail);
        return ResponseEntity.status(HttpStatus.OK).body(updatedOrderDetail);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id){
        orderDetailService.deleteOrderDetail(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
