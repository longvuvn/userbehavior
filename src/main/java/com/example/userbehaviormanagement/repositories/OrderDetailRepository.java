package com.example.userbehaviormanagement.repositories;

import com.example.userbehaviormanagement.entities.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, UUID> {
}
