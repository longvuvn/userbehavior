package com.example.userbehaviormanagement.controllers;

import com.example.userbehaviormanagement.entities.dto.ReviewDTO;
import com.example.userbehaviormanagement.services.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping
    public ResponseEntity<List<ReviewDTO>> getAll(){
        List<ReviewDTO> reviews = reviewService.getAllReviews();
        return ResponseEntity.status(HttpStatus.OK).body(reviews);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReviewDTO> getById(@PathVariable String id){
        ReviewDTO review = reviewService.getReviewById(id);
        return ResponseEntity.status(HttpStatus.OK).body(review);
    }

    @PostMapping
    public ResponseEntity<ReviewDTO> create (@RequestBody ReviewDTO review){
        ReviewDTO createdReview = reviewService.createReview(review);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdReview);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReviewDTO> update(@PathVariable String id, @RequestBody ReviewDTO review){
        ReviewDTO updatedReview = reviewService.updateReview(id, review);
        return ResponseEntity.status(HttpStatus.OK).body(updatedReview);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id){
        reviewService.deleteReview(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
