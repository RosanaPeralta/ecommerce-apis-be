package com.uade.tpo.grupo3.amancay.controllers;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.uade.tpo.grupo3.amancay.service.Products.*;
import com.uade.tpo.grupo3.amancay.entity.Product;
import com.uade.tpo.grupo3.amancay.entity.dto.common.GenericResponse;
import com.uade.tpo.grupo3.amancay.entity.dto.products.ProductRequest;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("products")
public class ProductsController {
    
    @Autowired
    private ProductsService productsService;

    @GetMapping
    public ResponseEntity<Page<Product>> getProducts(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size) {
        if (page == null || size == null)
            return ResponseEntity.ok(productsService.getProducts(PageRequest.of(0, Integer.MAX_VALUE)));
        return ResponseEntity.ok(productsService.getProducts(PageRequest.of(page, size)));
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable Long productId) {
        Optional<Product> result = productsService.getProductById(productId);
        if(result.isPresent())
         return ResponseEntity.ok(result.get());
        
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody ProductRequest request) {
        Product result = productsService.createProduct(request);
        return ResponseEntity.created(URI.create("/products/" + result.getId())).body(result);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<GenericResponse> updateProduct(@PathVariable Long productId, @RequestBody ProductRequest request) {
        GenericResponse result = productsService.updateProduct(productId, request);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<GenericResponse> deleteProduct(@PathVariable Long productId) {
        GenericResponse result = productsService.deleteProduct(productId);
        return ResponseEntity.ok(result);
    }
}
