package com.uade.tpo.grupo3.amancay.service.reviews;

import com.uade.tpo.grupo3.amancay.entity.Review;
import com.uade.tpo.grupo3.amancay.entity.User;
import com.uade.tpo.grupo3.amancay.entity.Product;
import com.uade.tpo.grupo3.amancay.entity.dto.common.GenericResponse;
import com.uade.tpo.grupo3.amancay.entity.dto.reviews.ReviewRequest;
import com.uade.tpo.grupo3.amancay.entity.dto.reviews.ReviewResponse;
import com.uade.tpo.grupo3.amancay.exceptions.DuplicateException;
import com.uade.tpo.grupo3.amancay.exceptions.NotFoundException;
import com.uade.tpo.grupo3.amancay.repository.ReviewRepository;
import com.uade.tpo.grupo3.amancay.repository.UserRepository;
import com.uade.tpo.grupo3.amancay.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public ReviewResponse createReview(ReviewRequest reviewRequest) throws DuplicateException {
        User user = userRepository.findById(reviewRequest.getUserId())
                .orElseThrow(() -> new NotFoundException("Usuario no encontrado con ID: " + reviewRequest.getUserId()));
        Product product = productRepository.findById(reviewRequest.getProductId())
                .orElseThrow(() -> new NotFoundException("Producto no encontrado con ID: " + reviewRequest.getProductId()));
        if (reviewRepository.existsByUserIdAndProductId(reviewRequest.getUserId(), reviewRequest.getProductId())) {
            throw new DuplicateException();
        }
        Review review = new Review();
        review.setUser(user);
        review.setProduct(product);
        review.setComment(reviewRequest.getComment());
        review.setRating(reviewRequest.getRating());
        Review savedReview = reviewRepository.save(review);
        return convertToResponse(savedReview);
    }

    @Override
    public Optional<ReviewResponse> getReviewById(Long id) {
        return reviewRepository.findById(id)
                .map(this::convertToResponse);
    }

    @Override
    public Page<ReviewResponse> getAllReviews(PageRequest pageRequest) {
        Page<Review> reviews = reviewRepository.findAll(pageRequest);
        List<ReviewResponse> reviewResponses = reviews.getContent().stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());

        return new PageImpl<>(reviewResponses, pageRequest, reviews.getTotalElements());
    }

    @Override
    public Page<ReviewResponse> getReviewsByProduct(Long productId, PageRequest pageRequest) {
        List<Review> reviews = reviewRepository.findByProductIdOrderByCreatedAtDesc(productId, pageRequest);
        List<ReviewResponse> reviewResponses = reviews.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());

        return new PageImpl<>(reviewResponses, pageRequest, reviewResponses.size());
    }

    @Override
    public Page<ReviewResponse> getReviewsByUser(Long userId, PageRequest pageRequest) {
        List<Review> reviews = reviewRepository.findByUserIdOrderByCreatedAtDesc(userId, pageRequest);
        List<ReviewResponse> reviewResponses = reviews.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());

        return new PageImpl<>(reviewResponses, pageRequest, reviewResponses.size());
    }

    @Override
    public Optional<ReviewResponse> getReviewByUserAndProduct(Long userId, Long productId) {
        return reviewRepository.findByUserIdAndProductId(userId, productId)
                .map(this::convertToResponse);
    }

    @Override
    public ReviewResponse updateReview(Long id, ReviewRequest reviewRequest) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Reseña no encontrada con ID: " + id));
        User user = userRepository.findById(reviewRequest.getUserId())
                .orElseThrow(() -> new NotFoundException("Usuario no encontrado con ID: " + reviewRequest.getUserId()));
        Product product = productRepository.findById(reviewRequest.getProductId())
                .orElseThrow(() -> new NotFoundException("Producto no encontrado con ID: " + reviewRequest.getProductId()));
        review.setUser(user);
        review.setProduct(product);
        review.setComment(reviewRequest.getComment());
        review.setRating(reviewRequest.getRating());

        Review updatedReview = reviewRepository.save(review);
        return convertToResponse(updatedReview);
    }

    @Override
    public GenericResponse deleteReview(Long id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Reseña no encontrada con ID: " + id));

        reviewRepository.delete(review);
        return new GenericResponse(id, "Reseña eliminada correctamente");
    }


    private ReviewResponse convertToResponse(Review review) {
        ReviewResponse response = new ReviewResponse();
        response.setId(review.getId());
        response.setComment(review.getComment());
        response.setRating(review.getRating());
        response.setCreatedAt(review.getCreatedAt());
        response.setUpdatedAt(review.getUpdatedAt());

        if (review.getUser() != null) {
            ReviewResponse.UserDto userDto = new ReviewResponse.UserDto();
            userDto.setId(review.getUser().getId());
            userDto.setFirstName(review.getUser().getFirstName());
            userDto.setLastName(review.getUser().getLastName());
            userDto.setEmail(review.getUser().getEmail());
            response.setUser(userDto);
        }
        if (review.getProduct() != null) {
            ReviewResponse.ProductDto productDto = new ReviewResponse.ProductDto();
            productDto.setId(review.getProduct().getId());
            productDto.setName(review.getProduct().getName());
            productDto.setDescription(review.getProduct().getDescription());
            productDto.setPrice(review.getProduct().getPrice());
            response.setProduct(productDto);
        }
        return response;
    }
}
