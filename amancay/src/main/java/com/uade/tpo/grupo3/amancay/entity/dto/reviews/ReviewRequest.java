package com.uade.tpo.grupo3.amancay.entity.dto.reviews;

import lombok.Data;

@Data
public class ReviewRequest {
    
    private Long userId;
    private Long productId;
    private String comment;
    private Integer rating;
}