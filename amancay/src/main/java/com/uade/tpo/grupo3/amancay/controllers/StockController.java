package com.uade.tpo.grupo3.amancay.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.uade.tpo.grupo3.amancay.entity.dto.stock.StockResponse;
import com.uade.tpo.grupo3.amancay.entity.dto.stock.StockUpdateRequest;
import com.uade.tpo.grupo3.amancay.service.stock.StockService;

@RestController
@RequestMapping("stock")
public class StockController {

    @Autowired
    private StockService stockService;

    @GetMapping("/{productId}")
    public ResponseEntity<StockResponse> getStock(@PathVariable Long productId) {
        StockResponse response = stockService.getStock(productId);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{productId}/increment")
    public ResponseEntity<StockResponse> incrementStock(
            @PathVariable Long productId,
            @RequestBody StockUpdateRequest request) {
        StockResponse response = stockService.incrementStock(productId, request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{productId}/decrement")
    public ResponseEntity<StockResponse> decrementStock(
            @PathVariable Long productId,
            @RequestBody StockUpdateRequest request) {
        StockResponse response = stockService.decrementStock(productId, request);
        return ResponseEntity.ok(response);
    }

    /**
     * Verifica si un producto tiene stock suficiente
     * GET /stock/{productId}/check?quantity=5
     */
    @GetMapping("/{productId}/check")
    public ResponseEntity<Boolean> checkStock(
            @PathVariable Long productId,
            @RequestParam int quantity) {
        boolean hasStock = stockService.hasStock(productId, quantity);
        return ResponseEntity.ok(hasStock);
    }
}
