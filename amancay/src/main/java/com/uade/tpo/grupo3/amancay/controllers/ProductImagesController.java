package com.uade.tpo.grupo3.amancay.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.uade.tpo.grupo3.amancay.entity.dto.common.GenericResponse;
import com.uade.tpo.grupo3.amancay.entity.dto.productImages.ProductImagesResponse;
import com.uade.tpo.grupo3.amancay.service.productImages.ProductImageService;

@RestController
@RequestMapping("product_images")
public class ProductImagesController {

    @Autowired
    private ProductImageService productImageService;

    @PostMapping("/{productId}")
    public GenericResponse uploadProductWithImages(@PathVariable Long productId,
            @RequestPart("files") MultipartFile[] files) {
        try {
            if (files == null || files.length == 0) {
                return new GenericResponse(null, "No files provided or files are empty");
            }

            productImageService.uploadImages(productId, files);

            return new GenericResponse(null, "Product uploaded successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return new GenericResponse(null, "Failed to upload product: " +
                    e.getMessage());
        }
    }

    @GetMapping("/{productId}")
    public ResponseEntity<List<ProductImagesResponse>> getImagesByProductId(@PathVariable Long productId) {
        List<ProductImagesResponse> images = productImageService.getImagesByProductId(productId);
        return ResponseEntity.ok(images);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<GenericResponse> deleteAllProductImages(@PathVariable Long productId) {
        GenericResponse result = productImageService.deleteAllProductImages(productId);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{productId}")
    public GenericResponse updateImages(@PathVariable Long productId,
            @RequestPart("files") MultipartFile[] files) {
        try {
            if (files == null || files.length == 0) {
                return new GenericResponse(null, "No files provided or files are empty");
            }

            productImageService.updateImages(productId, files);

            return new GenericResponse(null, "Product images updated successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return new GenericResponse(null, "Failed to update product images: " +
                    e.getMessage());
        }
    }
}