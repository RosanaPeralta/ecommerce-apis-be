package com.uade.tpo.grupo3.amancay.service.stock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;

import com.uade.tpo.grupo3.amancay.entity.Product;
import com.uade.tpo.grupo3.amancay.entity.dto.stock.StockResponse;
import com.uade.tpo.grupo3.amancay.entity.dto.stock.StockUpdateRequest;
import com.uade.tpo.grupo3.amancay.exceptions.NotFoundException;
import com.uade.tpo.grupo3.amancay.repository.ProductRepository;

@Service
public class StockServiceImpl implements StockService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public StockResponse getStock(Long productId) {
        if (productId == null || productId <= 0) {
            throw new InvalidParameterException("Invalid product ID");
        }
        
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException("Product not found with ID: " + productId));
        
        return new StockResponse(
            product.getId(),
            product.getName(),
            product.getStock(),
            product.getStock() > 0
        );
    }

    @Override
    public StockResponse incrementStock(Long productId, StockUpdateRequest request) {
        if (productId == null || productId <= 0) {
            throw new InvalidParameterException("Invalid product ID");
        }
        
        if (request == null || request.getQuantity() <= 0) {
            throw new InvalidParameterException("Quantity must be greater than 0");
        }
        
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException("Product not found with ID: " + productId));
        
        product.setStock(product.getStock() + request.getQuantity());
        productRepository.save(product);
        
        return new StockResponse(
            product.getId(),
            product.getName(),
            product.getStock(),
            product.getStock() > 0
        );
    }

    @Override
    public StockResponse decrementStock(Long productId, StockUpdateRequest request) {
        if (productId == null || productId <= 0) {
            throw new InvalidParameterException("Invalid product ID");
        }
        
        if (request == null || request.getQuantity() <= 0) {
            throw new InvalidParameterException("Quantity must be greater than 0");
        }
        
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException("Product not found with ID: " + productId));
        
        if (product.getStock() < request.getQuantity()) {
            throw new InvalidParameterException("Insufficient stock. Available: " + product.getStock() + 
                ", requested: " + request.getQuantity());
        }
        
        product.setStock(product.getStock() - request.getQuantity());
        productRepository.save(product);
        
        return new StockResponse(
            product.getId(),
            product.getName(),
            product.getStock(),
            product.getStock() > 0
        );
    }

    @Override
    public boolean hasStock(Long productId, int requiredQuantity) {
        if (productId == null || productId <= 0) {
            throw new InvalidParameterException("Invalid product ID");
        }
        
        if (requiredQuantity < 0) {
            throw new InvalidParameterException("Required quantity cannot be negative");
        }
        
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException("Product not found with ID: " + productId));
        
        return product.getStock() >= requiredQuantity;
    }
}
