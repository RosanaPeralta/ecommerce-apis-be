package com.uade.tpo.grupo3.amancay.entity.dto.products;

import java.util.List;

import com.uade.tpo.grupo3.amancay.entity.ProductImage;

import lombok.Data;

@Data
public class ProductRequest {
    private String description;
    private String name;
    private int stock;
    private Double price;
    private String status;
    private List<ProductImage> images;
    private Long categoryId;
    private List<Long> activityIds;  
    private Long discountId;

    public ProductRequest(String description, String name, int stock, Double price, String status, List<ProductImage> images, Long categoryId, List<Long> activityIds, Long discountId){
      this.description = description;
      this.name = name;
      this.stock = stock;
      this.price = price;
      this.status = status;
      this.images = images;
      this.categoryId = categoryId;
      this.activityIds = activityIds;
      this.discountId = discountId;
    }
}