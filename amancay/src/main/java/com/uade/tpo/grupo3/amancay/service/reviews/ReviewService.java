package com.uade.tpo.grupo3.amancay.service.reviews;

import com.uade.tpo.grupo3.amancay.entity.dto.common.GenericResponse;
import com.uade.tpo.grupo3.amancay.entity.dto.reviews.ReviewRequest;
import com.uade.tpo.grupo3.amancay.entity.dto.reviews.ReviewResponse;
import com.uade.tpo.grupo3.amancay.exceptions.DuplicateException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

public interface ReviewService {
    
    ReviewResponse createReview(ReviewRequest reviewRequest) throws DuplicateException;
    
    Optional<ReviewResponse> getReviewById(Long id);
    
    Page<ReviewResponse> getAllReviews(PageRequest pageRequest);
    
    Page<ReviewResponse> getReviewsByProduct(Long productId, PageRequest pageRequest);
    
    Page<ReviewResponse> getReviewsByUser(Long userId, PageRequest pageRequest);
    
    Optional<ReviewResponse> getReviewByUserAndProduct(Long userId, Long productId);
    
    ReviewResponse updateReview(Long id, ReviewRequest reviewRequest);
    
    GenericResponse deleteReview(Long id);
}
