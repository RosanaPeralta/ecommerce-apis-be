package com.uade.tpo.grupo3.amancay.service.Products;

import java.security.InvalidParameterException;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.uade.tpo.grupo3.amancay.entity.Product;
import com.uade.tpo.grupo3.amancay.entity.dto.common.GenericResponse;
import com.uade.tpo.grupo3.amancay.entity.dto.products.ProductRequest;

public interface ProductsService {
    public Page<Product> getProducts(PageRequest pageRequest);

    public Optional<Product> getProductById(Long id);

    public Product createProduct(ProductRequest product);

    public GenericResponse deleteProduct(Long id);

    public GenericResponse updateProduct(Long id, ProductRequest product) throws InvalidParameterException;
}
