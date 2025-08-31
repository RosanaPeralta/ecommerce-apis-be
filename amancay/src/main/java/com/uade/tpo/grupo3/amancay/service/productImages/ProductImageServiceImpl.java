package com.uade.tpo.grupo3.amancay.service.productImages;

import org.springframework.web.multipart.MultipartFile;
import com.uade.tpo.grupo3.amancay.entity.ProductImage;
import com.uade.tpo.grupo3.amancay.entity.dto.common.GenericResponse;
import com.uade.tpo.grupo3.amancay.entity.dto.productImages.ProductImagesResponse;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uade.tpo.grupo3.amancay.entity.Product;
import com.uade.tpo.grupo3.amancay.repository.ProductRepository;
import com.uade.tpo.grupo3.amancay.repository.ProductImageRepository;

import java.io.IOException;
import java.util.ArrayList;

@Service
@Transactional
public class ProductImageServiceImpl implements ProductImageService {

    @Autowired
    private ProductImageRepository productImageRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<ProductImagesResponse> uploadImages(Long productId, MultipartFile[] files) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + productId));

        List<ProductImagesResponse> productImagesResponses = new ArrayList<>();
        for (MultipartFile file : files) {
            try {

                ProductImage productImage = new ProductImage();
                productImage.setName(file.getName());
                productImage.setType(file.getContentType());
                productImage.setImageData(file.getBytes());
                productImage.setProduct(product);

                productImageRepository.save(productImage);
                productImagesResponses.add(new ProductImagesResponse(productImage.getName(), productImage.getType(),
                        productImage.getImageData(), productImage.getProduct().getId()));

            } catch (IOException e) {
                throw new RuntimeException("Error processing the file: " + file.getOriginalFilename(), e);
            }
        }

        return productImagesResponses;
    }

    @Override
    public List<ProductImagesResponse> getImagesByProductId(Long productId) {
        List<ProductImage> productImages = productImageRepository.findByProductId(productId);
        return productImages.stream().map(productImage -> new ProductImagesResponse(productImage.getName(),
                productImage.getType(), productImage.getImageData(), productImage.getProduct().getId()))
                .collect(Collectors.toList());
    }

    @Override
    public GenericResponse deleteAllProductImages(Long productId) {
        List<ProductImage> images = productImageRepository.findByProductId(productId);
        productImageRepository.deleteAll(images);
        return new GenericResponse(productId, "All images associated to the product have been deleted successfully");
    }

    @Override
    public List<ProductImagesResponse> updateImages(Long productId, MultipartFile[] files) {
        productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + productId));

        // Delete all images associated to the product
        this.deleteAllProductImages(productId);

        // Upload new images
        this.uploadImages(productId, files);

        return this.getImagesByProductId(productId);
    }

}
