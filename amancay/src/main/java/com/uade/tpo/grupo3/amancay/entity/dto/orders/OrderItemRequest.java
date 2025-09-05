package com.uade.tpo.grupo3.amancay.entity.dto.orders;

import lombok.Data;

@Data
public class OrderItemRequest {
    private Long productId;
    private Integer quantity;

    public OrderItemRequest() {
    }

    public OrderItemRequest(Long productId, Integer quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }
}

