package com.uade.tpo.grupo3.amancay.entity.dto.products;

import lombok.Data;

@Data
public class ProductRequest {
    private String description;
    private String name;
    private int stock;
    private Double price;
    private String status;
    private String imageUrl;
    private Long categoryId;
    private Long activityId;
    private Long discountId;

    public ProductRequest(String description, String name, int stock, Double price, String status, String imageUrl, Long categoryId, Long activityId, Long discountId){
      this.description = description;
      this.name = name;
      this.stock = stock;
      this.price = price;
      this.status = status;
      this.imageUrl = imageUrl;
      this.categoryId = categoryId;
      this.activityId = activityId;
      this.discountId = discountId;
    }
}
