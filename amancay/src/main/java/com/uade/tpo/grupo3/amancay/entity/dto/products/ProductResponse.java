package com.uade.tpo.grupo3.amancay.entity.dto.products;

import lombok.Data;
import java.util.List;

@Data
public class ProductResponse {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private int stock;
    private String status;
    private String imageUrl;
    private CategoryDto category;
    private ActivityDto activity;
    private DiscountDto discount;
    private List<ProductImageDto> images;

    @Data
    public static class CategoryDto {
        private Long id;
        private String name;
    }

    @Data
    public static class ActivityDto {
        private Long id;
        private String name;
    }

    @Data
    public static class DiscountDto {
        private Long id;
        private String name;
        private Double percentage;
    }

    @Data
    public static class ProductImageDto {
        private Long id;
        private String name;
        private String type;
        private byte[] imageData;
    }
}
