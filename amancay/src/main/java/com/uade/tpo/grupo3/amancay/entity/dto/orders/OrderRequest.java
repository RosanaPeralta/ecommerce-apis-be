package com.uade.tpo.grupo3.amancay.entity.dto.orders;

import lombok.Data;

@Data
public class OrderRequest {
    private Long customerId;
    private Long productId;
    private int quantity;
    private float price;
    private String status;
    private String notes;

    public OrderRequest(Long customerId, Long productId, int quantity, float price, String status, String notes) {
        this.customerId = customerId;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
        this.status = status;
        this.notes = notes;
    }
}
