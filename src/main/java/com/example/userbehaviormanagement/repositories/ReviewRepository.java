package com.example.userbehaviormanagement.repositories;

import com.example.userbehaviormanagement.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ReviewRepository extends JpaRepository<Review, UUID> {
    int sumRatingByProductId(UUID productId);

    int countByProductId(UUID productId);
}
