package com.uade.tpo.grupo3.amancay.service.Products;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.uade.tpo.grupo3.amancay.repository.*;

import com.uade.tpo.grupo3.amancay.entity.Product;
import com.uade.tpo.grupo3.amancay.entity.dto.common.GenericResponse;
import com.uade.tpo.grupo3.amancay.entity.dto.products.ProductRequest;

@Service
public class ProductsServiceImpl implements ProductsService  {

    @Autowired
    private ProductRepository productRepository;

    public Page<Product> getProducts(PageRequest pageable) {
        return productRepository.findAll(pageable);
    }

    public Optional<Product> getProductById(Long productId) {
        return productRepository.findById(productId);
    }

    public Product createProduct(ProductRequest productRequest) {
        Product product = new Product();
        product.setName(productRequest.getName());
        product.setDescription(productRequest.getDescription());
        product.setPrice(productRequest.getPrice());
        product.setStock(productRequest.getStock());

        //todo: setear datos (ver si va a ser un listado o no)
        // product.setActivity(null);
        // product.setCategory(null);
        // product.setDiscount(null);
        //prohibir que sea nulleable (siempre debe tener una imagen el articulo)
        product.setImageUrl(null);
        return productRepository.save(product);
    }

    public GenericResponse deleteProduct(Long productId) {
        productRepository.deleteById(productId);
        return new GenericResponse(productId, "Producto eliminado correctamente");
    }

    public GenericResponse updateProduct(Long productId, ProductRequest productRequest) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("No se encontró el producto con id: " + productId));
        product.setName(productRequest.getName());
        product.setDescription(productRequest.getDescription());
        product.setPrice(productRequest.getPrice());
        product.setStock(productRequest.getStock());

        //todo: setear actividad, categoria y descuento
        product.setImageUrl(productRequest.getImageUrl());
        productRepository.save(product);
        return new GenericResponse(product.getId(), "Producto actualizado correctamente");
    }


}
