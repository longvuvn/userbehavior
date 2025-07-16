package com.example.userbehaviormanagement.services;

import com.example.userbehaviormanagement.entities.dto.ReviewDTO;

import java.util.List;

public interface ReviewService {
    List<ReviewDTO> getAllReviews();
    ReviewDTO getReviewById(String id);
    ReviewDTO createReview(ReviewDTO reviewDTO);
    ReviewDTO updateReview(String id, ReviewDTO reviewDTO);
    void deleteReview(String id);
}
