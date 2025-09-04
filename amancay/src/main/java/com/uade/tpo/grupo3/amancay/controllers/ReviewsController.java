package com.uade.tpo.grupo3.amancay.controllers;

import com.uade.tpo.grupo3.amancay.entity.dto.common.GenericResponse;
import com.uade.tpo.grupo3.amancay.entity.dto.reviews.ReviewRequest;
import com.uade.tpo.grupo3.amancay.entity.dto.reviews.ReviewResponse;
import com.uade.tpo.grupo3.amancay.exceptions.DuplicateException;
import com.uade.tpo.grupo3.amancay.service.reviews.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/reviews")
public class ReviewsController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping
    public ResponseEntity<ReviewResponse> createReview(@RequestBody ReviewRequest reviewRequest) throws DuplicateException {
        ReviewResponse result = reviewService.createReview(reviewRequest);
        return ResponseEntity.created(URI.create("/reviews/" + result.getId())).body(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReviewResponse> getReviewById(@PathVariable Long id) {
        Optional<ReviewResponse> result = reviewService.getReviewById(id);
        if (result.isPresent()) {
            return ResponseEntity.ok(result.get());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<Page<ReviewResponse>> getAllReviews(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<ReviewResponse> reviews = reviewService.getAllReviews(pageRequest);
        
        if (reviews.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        
        return ResponseEntity.ok(reviews);
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<Page<ReviewResponse>> getReviewsByProduct(
            @PathVariable Long productId,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<ReviewResponse> reviews = reviewService.getReviewsByProduct(productId, pageRequest);
        
        if (reviews.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        
        return ResponseEntity.ok(reviews);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Page<ReviewResponse>> getReviewsByUser(
            @PathVariable Long userId,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<ReviewResponse> reviews = reviewService.getReviewsByUser(userId, pageRequest);
        
        if (reviews.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        
        return ResponseEntity.ok(reviews);
    }

    @GetMapping("/user/{userId}/product/{productId}")
    public ResponseEntity<ReviewResponse> getReviewByUserAndProduct(
            @PathVariable Long userId,
            @PathVariable Long productId) {
        Optional<ReviewResponse> result = reviewService.getReviewByUserAndProduct(userId, productId);
        if (result.isPresent()) {
            return ResponseEntity.ok(result.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReviewResponse> updateReview(
            @PathVariable Long id,
            @RequestBody ReviewRequest reviewRequest) {
        ReviewResponse result = reviewService.updateReview(id, reviewRequest);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GenericResponse> deleteReview(@PathVariable Long id) {
        GenericResponse result = reviewService.deleteReview(id);
        return ResponseEntity.ok(result);
    }


}
