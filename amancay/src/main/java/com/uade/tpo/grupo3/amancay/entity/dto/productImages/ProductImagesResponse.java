package com.uade.tpo.grupo3.amancay.entity.dto.productImages;

import lombok.Data;

@Data
public class ProductImagesResponse {
    private String name;
    private String type;
    private byte[] imageData;
    private Long productId;

    public ProductImagesResponse(String name, String type, byte[] imageData, Long productId) {
        this.name = name;
        this.type = type;
        this.imageData = imageData;
        this.productId = productId;
    }

}