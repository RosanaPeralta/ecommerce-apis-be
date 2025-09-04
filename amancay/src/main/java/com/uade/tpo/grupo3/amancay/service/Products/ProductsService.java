package com.uade.tpo.grupo3.amancay.service.Products;

import java.security.InvalidParameterException;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.uade.tpo.grupo3.amancay.entity.dto.common.GenericResponse;
import com.uade.tpo.grupo3.amancay.entity.dto.discounts.DiscountRequest;
import com.uade.tpo.grupo3.amancay.entity.dto.products.ProductRequest;
import com.uade.tpo.grupo3.amancay.entity.dto.products.ProductResponse;

public interface ProductsService {
    public Page<ProductResponse> getProducts(PageRequest pageRequest);

    public Optional<ProductResponse> getProductById(Long id);

    public ProductResponse createProduct(ProductRequest product);

    public GenericResponse deleteProduct(Long id) throws InvalidParameterException;

    public GenericResponse updateProduct(Long id, ProductRequest product) throws InvalidParameterException;

    public Page<ProductResponse> getFilteredProducts(PageRequest pageRequest, Long categoryId, Long activityId,
            Double minPrice,
            Double maxPrice, boolean withStock);

    ProductResponse assignNewDiscountToProduct(Long productId, DiscountRequest request);

    /**
     * Asigna un descuento EXISTENTE a un producto.
     */
    ProductResponse assignExistingDiscountToProduct(Long productId, Long discountId) throws InvalidParameterException;

    /**
     * Crea un descuento nuevo y lo asigna al producto.
     */
    ProductResponse createAndAssignDiscount(Long productId, DiscountRequest request)
            throws InvalidParameterException;

    /**
     * Quita el descuento asociado del producto (lo deja en null).
     */
    GenericResponse removeDiscountFromProduct(Long productId) throws InvalidParameterException;
}
