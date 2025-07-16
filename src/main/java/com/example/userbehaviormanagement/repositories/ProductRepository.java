package com.example.userbehaviormanagement.repositories;

import com.example.userbehaviormanagement.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
}
