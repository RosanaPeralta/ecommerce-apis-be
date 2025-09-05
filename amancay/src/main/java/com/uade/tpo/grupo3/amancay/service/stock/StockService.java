package com.uade.tpo.grupo3.amancay.service.stock;

import com.uade.tpo.grupo3.amancay.entity.dto.stock.StockResponse;
import com.uade.tpo.grupo3.amancay.entity.dto.stock.StockUpdateRequest;

public interface StockService {
    
    /**
     * Obtiene el stock actual de un producto
     */
    StockResponse getStock(Long productId);
    
    /**
     * Incrementa el stock de un producto
     */
    StockResponse incrementStock(Long productId, StockUpdateRequest request);
    
    /**
     * Decrementa el stock de un producto
     */
    StockResponse decrementStock(Long productId, StockUpdateRequest request);
    
    /**
     * Verifica si un producto tiene stock disponible
     */
    boolean hasStock(Long productId, int requiredQuantity);
}
