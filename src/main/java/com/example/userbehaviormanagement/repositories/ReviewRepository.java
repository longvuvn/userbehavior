package com.example.userbehaviormanagement.repositories;

import com.example.userbehaviormanagement.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface ReviewRepository extends JpaRepository<Review, UUID> {

    @Query("SELECT SUM(r.rating) FROM Review r WHERE r.product.id = :productId")
    int sumRatingByProductId(UUID productId);

    int countByProductId(UUID productId);

    //lay tất cả review của 1 product
    @Query("SELECT r FROM Review r WHERE r.product.id = :productId")
    List<Review> findAllByProductId(UUID productId);
}
