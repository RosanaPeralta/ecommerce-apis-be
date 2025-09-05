package com.uade.tpo.grupo3.amancay.entity.dto.stock;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockResponse {
    private Long productId;
    private String productName;
    private int currentStock;
    private boolean inStock;
}
