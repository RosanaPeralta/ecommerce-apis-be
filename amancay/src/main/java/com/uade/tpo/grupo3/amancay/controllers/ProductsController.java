package com.uade.tpo.grupo3.amancay.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.uade.tpo.grupo3.amancay.entity.Product;
import com.uade.tpo.grupo3.amancay.entity.dto.ProductRequest;
import com.uade.tpo.grupo3.amancay.service.ProductService;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductsController {

    @Autowired
    private ProductService productService;

    // GET /product → Catálogo
    @GetMapping
    public ResponseEntity<Page<Product>> getProducts(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size) {
        
        if (page == null || size == null) {
            return ResponseEntity.ok(productService.getProduct(PageRequest.of(0, Integer.MAX_VALUE)));
        }
        
        return ResponseEntity.ok(productService.getProduct(PageRequest.of(page, size)));
    }

    // GET /product/{id} → Detalle
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Optional<Product> product = productService.getProductById(id);
        if (product.isPresent()) {
            return ResponseEntity.ok(product.get());
        }
        return ResponseEntity.notFound().build();
    }

    // POST /product/add → Creación
    @PostMapping("/add")
    public ResponseEntity<Product> createProduct(@RequestBody ProductRequest productRequest) {
        try {
            Product newProduct = productService.createProduct(productRequest.getDescription());
            return ResponseEntity.created(URI.create("/products/" + newProduct.getId())).body(newProduct);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // PUT /product/{id} → Modificación de un artículo
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody ProductRequest productRequest) {
        Optional<Product> existingProduct = productService.getProductById(id);
    }

    // DELETE /product/{id} → Eliminación de un artículo
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        Optional<Product> existingProduct = productService.getProductById(id);
        
        if (existingProduct.isPresent()) {
            productService.deleteProduct(id);
            return ResponseEntity.noContent().build();
        }
        
        return ResponseEntity.notFound().build();
    }

    // GET /product/{idCategoria}/{idActividad}/{rangoPrecio} → Filtro de artículos
/*  @GetMapping("/{idCategoria}/{idActividad}/{rangoPrecio}")
    public ResponseEntity<Page<Product>> getFilteredProducts(
            @PathVariable Long idCategoria,
            @PathVariable Long idActividad,
            @PathVariable String rangoPrecio) {
        
    }
*/ 

/*  // Stock de un producto
    @GetMapping("/{id}/stock")
    public ResponseEntity<Integer> getProductStock(@PathVariable Long id) {
        Optional<Product> product = productService.getProductById(id);
    }

    // Gestionar stock
    @PutMapping("/{id}/stock")
    public ResponseEntity<Void> updateProductStock(@PathVariable Long id, @RequestParam int cantidad) {
        Optional<Product> product = productService.getProductById(id);
    }
        */
} 