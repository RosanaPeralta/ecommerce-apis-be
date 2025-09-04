package com.uade.tpo.grupo3.amancay.controllers;

import java.net.URI;
import java.security.InvalidParameterException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uade.tpo.grupo3.amancay.entity.dto.common.GenericResponse;
import com.uade.tpo.grupo3.amancay.entity.dto.discounts.DiscountRequest;
import com.uade.tpo.grupo3.amancay.entity.dto.products.ProductRequest;
import com.uade.tpo.grupo3.amancay.entity.dto.products.ProductResponse;
import com.uade.tpo.grupo3.amancay.service.Products.ProductsService;

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

    @GetMapping("/all")
    public ResponseEntity<Page<ProductResponse>> getProducts(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size) {
        if (page == null || size == null)
            return ResponseEntity.ok(productsService.getProducts(PageRequest.of(0, Integer.MAX_VALUE)));
        return ResponseEntity.ok(productsService.getProducts(PageRequest.of(page, size)));
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable Long productId) {
        Optional<ProductResponse> result = productsService.getProductById(productId);
        if (result.isPresent())
            return ResponseEntity.ok(result.get());

        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductRequest request) {
        ProductResponse result = productsService.createProduct(request);
        return ResponseEntity.created(URI.create("/products/" + result.getId())).body(result);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<GenericResponse> updateProduct(@PathVariable Long productId,
            @RequestBody ProductRequest request) {
        GenericResponse result = productsService.updateProduct(productId, request);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<GenericResponse> deleteProduct(@PathVariable Long productId)
            throws InvalidParameterException {
        GenericResponse result = productsService.deleteProduct(productId);
        return ResponseEntity.ok(result);
    }

    @GetMapping
    public ResponseEntity<Page<ProductResponse>> getFilteredProducts(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) Long activityId,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(required = false, defaultValue = "false") boolean withStock) {

        PageRequest pageRequest = PageRequest.of(page, size);

        Page<ProductResponse> products = productsService.getFilteredProducts(
                pageRequest, categoryId, activityId, minPrice, maxPrice, withStock);

        if (products.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(products);
    }

    // ===================== Descuentos de un producto =====================

    /**
     * Crea un descuento y lo asigna al producto.
     * Body ejemplo:
     * {
     * "percentage": 15.0,
     * "description": "Vuelta al cole"
     * }
     */
    @PostMapping("/{productId}/discounts")
    public ResponseEntity<ProductResponse> createAndAssignDiscount(
            @PathVariable Long productId,
            @RequestBody DiscountRequest request) {

        ProductResponse updated = productsService.assignNewDiscountToProduct(productId, request);
        return ResponseEntity.ok(updated);
    }

    /**
     * Asigna un descuento existente al producto.
     */
    @PutMapping("/{productId}/discounts/{discountId}")
    public ResponseEntity<ProductResponse> assignExistingDiscount(
            @PathVariable Long productId,
            @PathVariable Long discountId) {

        ProductResponse updated = productsService.assignExistingDiscountToProduct(productId, discountId);
        return ResponseEntity.ok(updated);
    }

    /**
     * Quita el descuento del producto.
     */
    @DeleteMapping("/{productId}/discounts")
    public ResponseEntity<GenericResponse> removeDiscount(@PathVariable Long productId) {
        GenericResponse res = productsService.removeDiscountFromProduct(productId);
        return ResponseEntity.ok(res);
    }

}
