package com.uade.tpo.grupo3.amancay.entity.dto.orders;

import lombok.Data;

@Data
public class OrderItemRequest {
    private Long productId;
    private Integer quantity;
    private Double unitPrice;

    public OrderItemRequest() {
    }

    public OrderItemRequest(Long productId, Integer quantity, Double unitPrice) {
        this.productId = productId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }
}

