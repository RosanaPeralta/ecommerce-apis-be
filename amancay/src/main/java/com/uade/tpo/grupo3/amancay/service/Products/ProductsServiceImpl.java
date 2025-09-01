package com.uade.tpo.grupo3.amancay.service.products;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uade.tpo.grupo3.amancay.repository.*;
import com.uade.tpo.grupo3.amancay.entity.Product;
import com.uade.tpo.grupo3.amancay.entity.ProductImage;
import com.uade.tpo.grupo3.amancay.entity.Category;
import com.uade.tpo.grupo3.amancay.entity.Activity;
import com.uade.tpo.grupo3.amancay.entity.dto.common.GenericResponse;
import com.uade.tpo.grupo3.amancay.entity.dto.products.ProductRequest;
import com.uade.tpo.grupo3.amancay.exceptions.NotFoundException;
import com.uade.tpo.grupo3.amancay.entity.dto.products.ProductResponse;

@Service
@Transactional
public class ProductsServiceImpl implements ProductsService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ActivityRepository activityRepository;

    public Page<ProductResponse> getProducts(PageRequest pageable) {
        Page<Product> products = productRepository.findAll(pageable);
        List<ProductResponse> productResponses = products.getContent().stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());

        return new PageImpl<>(productResponses, pageable, products.getTotalElements());
    }

    public Optional<ProductResponse> getProductById(Long productId) {
        return productRepository.findById(productId)
                .map(this::convertToResponse);
    }

    public ProductResponse createProduct(ProductRequest productRequest) {
        Product product = new Product();
        product.setName(productRequest.getName());
        product.setDescription(productRequest.getDescription());
        product.setPrice(productRequest.getPrice());
        product.setStock(productRequest.getStock());
        product.setStatus("ACTIVE");

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

        Product savedProduct = productRepository.save(product);
        return convertToResponse(savedProduct);
    }

    public GenericResponse deleteProduct(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new NotFoundException("No se encontró el producto con ID " + productId));
        
        productRepository.delete(product);
        
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

        productRepository.saveAndFlush(product);
        return new GenericResponse(productId, "Producto actualizado correctamente");
    }

    public Page<ProductResponse> getFilteredProducts(PageRequest pageRequest, Long categoryId, Long activityId, Double minPrice,
            Double maxPrice, boolean withStock) {
        if (categoryId != null || activityId != null || minPrice != null || maxPrice != null || withStock == true) {
            List<Product> products = productRepository.findByFilters(categoryId, activityId, minPrice, maxPrice,
                    withStock, pageRequest);
            return new PageImpl<>(products.stream().map(this::convertToResponse).collect(Collectors.toList()), pageRequest, products.size());
        }

        return this.getProducts(pageRequest);
    }

    /**
     * Aux method to convert Product to ProductResponse
     * 
     * @param product
     * @return ProductResponse
     */
    private ProductResponse convertToResponse(Product product) {
        ProductResponse response = new ProductResponse();
        response.setId(product.getId());
        response.setName(product.getName());
        response.setDescription(product.getDescription());
        response.setPrice(product.getPrice());
        response.setStock(product.getStock());
        response.setStatus(product.getStatus());

        // Convert category
        if (product.getCategory() != null) {
            ProductResponse.CategoryDto categoryDto = new ProductResponse.CategoryDto();
            categoryDto.setId(product.getCategory().getId());
            categoryDto.setName(product.getCategory().getName());
            response.setCategory(categoryDto);
        }

        // Convert activity
        if (product.getActivity() != null) {
            ProductResponse.ActivityDto activityDto = new ProductResponse.ActivityDto();
            activityDto.setId(product.getActivity().getId());
            activityDto.setName(product.getActivity().getName());
            response.setActivity(activityDto);
        }

        // Convert images
        if (product.getImages() != null) {
            response.setImages(new ArrayList<>());
            for (ProductImage productImage : product.getImages()) {
                ProductResponse.ProductImageDto productImageDto = new ProductResponse.ProductImageDto();
                productImageDto.setId(productImage.getId());
                productImageDto.setName(productImage.getName());
                productImageDto.setType(productImage.getType());
                productImageDto.setImageData(productImage.getImageData());
                response.getImages().add(productImageDto);
            }
        }

        return response;
    }

}