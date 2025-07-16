package com.example.userbehaviormanagement.repositories;

import com.example.userbehaviormanagement.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {
}
