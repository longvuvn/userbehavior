package com.example.userbehaviormanagement.services.Impl;

import com.example.userbehaviormanagement.entities.Customer;
import com.example.userbehaviormanagement.entities.Product;
import com.example.userbehaviormanagement.entities.Review;
import com.example.userbehaviormanagement.entities.dto.ReviewDTO;
import com.example.userbehaviormanagement.enums.ReviewStatus;
import com.example.userbehaviormanagement.repositories.CustomerRepository;
import com.example.userbehaviormanagement.repositories.ProductRepository;
import com.example.userbehaviormanagement.repositories.ReviewRepository;
import com.example.userbehaviormanagement.services.ProductService;
import com.example.userbehaviormanagement.services.ReviewService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final ProductService productService;
    private final ModelMapper modelMapper;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;

    @Override
    public List<ReviewDTO> getAllReviews() {
        List<Review> reviews = reviewRepository.findAll();
        return reviews.stream()
                .map(review -> modelMapper.map(review, ReviewDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public ReviewDTO getReviewById(String id) {
        UUID reviewId = UUID.fromString(id);
        Optional<Review> reviewOptional = reviewRepository.findById(reviewId);
        return reviewOptional.map(review -> modelMapper.map(review, ReviewDTO.class))
                .orElseThrow(() -> new RuntimeException("Review not found with id: " + id));
    }

    @Override
    public ReviewDTO createReview(ReviewDTO reviewDTO) {
        Review review = modelMapper.map(reviewDTO, Review.class);
        UUID productId = UUID.fromString(reviewDTO.getProductId());
        UUID customerId = UUID.fromString(reviewDTO.getCustomerId());

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + productId));
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + customerId));

        review.setRating(Integer.parseInt(reviewDTO.getRating()));
        review.setProduct(product);
        review.setCustomer(customer);
        review.setStatus(ReviewStatus.APPROVED);
        review = reviewRepository.save(review);
        productService.updateTotalRating(String.valueOf(product.getId()));
        return modelMapper.map(review, ReviewDTO.class);
    }

    @Override
    public ReviewDTO updateReview(String id, ReviewDTO reviewDTO) {
        UUID reviewId = UUID.fromString(id);
        Review existingReview = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new RuntimeException("Review not found with id: " + id));
        modelMapper.map(reviewDTO, existingReview);
        existingReview.setRating(Integer.parseInt(reviewDTO.getRating()));
        Review updatedReview = reviewRepository.save(existingReview);
        productService.updateTotalRating(String.valueOf(updatedReview.getProduct().getId()));
        return modelMapper.map(updatedReview, ReviewDTO.class);
    }

    @Override
    public void deleteReview(String id) {
        UUID reviewId = UUID.fromString(id);
        Review existingReview = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new RuntimeException("Review not found with id: " + id));
        reviewRepository.delete(existingReview);
        productService.updateTotalRating(String.valueOf(existingReview.getProduct().getId()));
    }
}
