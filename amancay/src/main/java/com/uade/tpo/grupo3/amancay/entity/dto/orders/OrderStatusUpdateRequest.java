package com.uade.tpo.grupo3.amancay.entity.dto.orders;

import lombok.Data;

@Data
public class OrderStatusUpdateRequest {
    private String status;
    private String notes;

    public OrderStatusUpdateRequest(String status, String notes) {
        this.status = status;
        this.notes = notes;
    }
}
