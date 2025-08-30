package com.uade.tpo.grupo3.amancay.entity.dto.orders;

import java.util.List;

import com.uade.tpo.grupo3.amancay.entity.Product;

import lombok.Data;

@Data
public class OrderRequest {
    private Long customerId;
    private List<Product> products;
    private String status;
    private String notes;

    public OrderRequest(Long customerId, List<Product> products, String status, String notes) {
        this.customerId = customerId;
        this.products = products;
        this.status = status;
        this.notes = notes;
    }
}
