
package com.uade.tpo.grupo3.amancay.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.uade.tpo.grupo3.amancay.entity.Activity;
import com.uade.tpo.grupo3.amancay.entity.Product;
import com.uade.tpo.grupo3.amancay.exceptions.DuplicateException;
import com.uade.tpo.grupo3.amancay.repository.ProductRepository;

public class ProductServiceImplementation implements ProductService {
    
    @Autowired
    private ProductRepository productRepository;

    public Page<Product> getProduct(PageRequest pageRequest){
        return productRepository.findAll(pageRequest);
    }

    public Optional<Product> getProductById(Long productId){
        return productRepository.findById(productId);
    }

    public Product createProduct(String description) throws DuplicateException{
       List<Product> products = productRepository.findByDescription(description);
        if (products.isEmpty())
            return productRepository.save(new Product(description));
        throw new DuplicateException(); 
    }

    public void deleteProduct(Long productId){
        productRepository.deleteById(productId);
    }

    public Page<Product> getProductsFiltered(Long categoryId, float minPrice, float maxPrice, Activity activity){

    }

    public void updateProduct(Long productId){

    }

    public void manageStock(int update){
        
    }

    public int getStock(Long productId){

    }
   
}