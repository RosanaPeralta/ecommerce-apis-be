package com.uade.tpo.grupo3.amancay.service.Products;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.uade.tpo.grupo3.amancay.repository.*;

import com.uade.tpo.grupo3.amancay.entity.Product;
import com.uade.tpo.grupo3.amancay.entity.Category;
import com.uade.tpo.grupo3.amancay.entity.Activity;
import com.uade.tpo.grupo3.amancay.entity.dto.common.GenericResponse;
import com.uade.tpo.grupo3.amancay.entity.dto.products.ProductRequest;

@Service
public class ProductsServiceImpl implements ProductsService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ActivityRepository activityRepository;

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
        product.setStatus("ACTIVE");
        product.setImageUrl(productRequest.getImageUrl());
        System.out.println("categoryId: " + productRequest.getCategoryId());
        System.out.println("activityIds: " + productRequest.getActivityIds());
        // Set category if is provided, and verify if the category exists
        if (productRequest.getCategoryId() != null) {
            Category category = categoryRepository.findById(productRequest.getCategoryId())
                    .orElse(null);
            product.setCategory(category);
        }

        // Set activity if is provided, and verify if the activity exists
        if (productRequest.getActivityId() != null) {
            Activity activity = activityRepository.findById(productRequest.getActivityId())
                    .orElse(null);
            product.setActivity(activity);
        }

        return productRepository.save(product);
    }

    public GenericResponse deleteProduct(Long productId) {
        productRepository.deleteById(productId);
        return new GenericResponse(productId, "Producto eliminado correctamente");
    }

    public GenericResponse updateProduct(Long productId, ProductRequest productRequest) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("No se encontró el producto con id: " + productId));
        product.setName(productRequest.getName() != null ? productRequest.getName() : product.getName());
        product.setDescription(
                productRequest.getDescription() != null ? productRequest.getDescription() : product.getDescription());
        product.setPrice(productRequest.getPrice() != null ? productRequest.getPrice() : product.getPrice());
        product.setStatus(productRequest.getStatus() != null ? productRequest.getStatus() : product.getStatus());
        product.setStock(
                productRequest.getStock() != product.getStock() && productRequest.getStock() > 0
                        ? productRequest.getStock()
                        : product.getStock());
        product.setImageUrl(
                productRequest.getImageUrl() != null ? productRequest.getImageUrl() : product.getImageUrl());

        // Set category if is provided, and verify if the category exists
        if (productRequest.getCategoryId() != null) {
            Category category = categoryRepository.findById(productRequest.getCategoryId())
                    .orElse(null);
            product.setCategory(category != null ? category : product.getCategory());
        }

        // Set activity if is provided, and verify if the activity exists
        if (productRequest.getActivityId() != null) {
            Activity activity = activityRepository.findById(productRequest.getActivityId())
                    .orElse(null);
            product.setActivity(activity != null ? activity : product.getActivity());
        }

        productRepository.save(product);
        return new GenericResponse(product.getId(), "Producto actualizado correctamente");
    }

    public Page<Product> getFilteredProducts(PageRequest pageRequest, Long categoryId, Long activityId, Double minPrice,
            Double maxPrice) {
        if (categoryId != null || activityId != null || minPrice != null || maxPrice != null) {
            List<Product> products = productRepository.findByFilters(categoryId, activityId, minPrice, maxPrice,
                    pageRequest);
            return new PageImpl<>(products, pageRequest, products.size());
        }

        return this.getProducts(pageRequest);
    }

}
