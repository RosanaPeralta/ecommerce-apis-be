
package com.uade.tpo.grupo3.amancay.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.uade.tpo.grupo3.amancay.entity.Activity;
import com.uade.tpo.grupo3.amancay.entity.Product;
import com.uade.tpo.grupo3.amancay.exceptions.DuplicateException;

public interface ProductService {
    
    public Page<Product> getProduct(PageRequest pageRequest);

    public Optional<Product> getProductById(Long productId); //Detalle de los artículos

    public Product createProduct(String description) throws DuplicateException;

    public void deleteProduct(Long productId);

    public Page<Product> getProductsFiltered(Long productId, float minPrice, float maxPrice, Activity activity);
    
    public void updateProduct(Long productId);

    public void manageStock(int update);

    public int getStock(Long productId);
}