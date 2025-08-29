package com.uade.tpo.grupo3.amancay.entity.dto.orders;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class OrderResponse {
    private Long id;
    private Long customerId;
    private Long productId;
    private String productName;
    private int quantity;
    private float price;
    private String status;
    private LocalDateTime orderDate;
    private LocalDateTime updatedDate;
    private String notes;

    public OrderResponse(Long id, Long customerId, Long productId, String productName, 
                        int quantity, float price, String status, 
                        LocalDateTime orderDate, LocalDateTime updatedDate, String notes) {
        this.id = id;
        this.customerId = customerId;
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.status = status;
        this.orderDate = orderDate;
        this.updatedDate = updatedDate;
        this.notes = notes;
    }
}
