package com.uade.tpo.grupo3.amancay.entity.dto.orders;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderResponse {
    private Long id;
    private Long customerId;
    private List<OrderItemResponse> items;
    private String status;
    private Double totalAmount;
    private LocalDateTime orderDate;
    private LocalDateTime updatedDate;
    private String notes;

    public OrderResponse() {
    }

    public OrderResponse(Long id, Long customerId, List<OrderItemResponse> items, 
                        String status, Double totalAmount, 
                        LocalDateTime orderDate, LocalDateTime updatedDate, String notes) {
        this.id = id;
        this.customerId = customerId;
        this.items = items;
        this.status = status;
        this.totalAmount = totalAmount;
        this.orderDate = orderDate;
        this.updatedDate = updatedDate;
        this.notes = notes;
    }
}
