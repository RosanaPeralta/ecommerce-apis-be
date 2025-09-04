package com.uade.tpo.grupo3.amancay.entity.dto.reviews;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ReviewResponse {
    private Long id;
    private UserDto user;
    private ProductDto product;
    private String comment;
    private Integer rating;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Data
    public static class UserDto {
        private Long id;
        private String firstName;
        private String lastName;
        private String email;
    }

    @Data
    public static class ProductDto {
        private Long id;
        private String name;
        private String description;
        private Double price;
    }
}
