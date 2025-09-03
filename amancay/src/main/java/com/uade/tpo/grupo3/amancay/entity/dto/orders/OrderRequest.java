package com.uade.tpo.grupo3.amancay.entity.dto.orders;

import java.util.List;

import lombok.Data;

@Data
public class OrderRequest {
    private Long customerId;
    private List<OrderItemRequest> items;
    private String status;
    private String notes;

    public OrderRequest() {
    }

    public OrderRequest(Long customerId, List<OrderItemRequest> items, String status, String notes) {
        this.customerId = customerId;
        this.items = items;
        this.status = status;
        this.notes = notes;
    }
}
