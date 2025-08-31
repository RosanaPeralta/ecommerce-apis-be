package com.uade.tpo.grupo3.amancay.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.uade.tpo.grupo3.amancay.entity.ProductImage;
import com.uade.tpo.grupo3.amancay.entity.dto.common.GenericResponse;
import com.uade.tpo.grupo3.amancay.service.product_images.ProductImageService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("productImages")
public class ProductImageController {

    @Autowired
    private ProductImageService productImageService;



    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<GenericResponse> uploadOne(
            @RequestPart("file") MultipartFile file,
            @RequestParam(value = "description", required = false) String description) throws IOException
    {

         System.out.println("Is empty: " + (file != null ? file.isEmpty() : "null"));
         System.out.println("Description: " + description);

         var result = new GenericResponse(null, description);

        return ResponseEntity.ok( result);
    }


    // @PostMapping
    // public GenericResponse uploadProductWithImage(@PathVariable Long productId,
    //         @RequestBody MultipartFile file) {
    //     try {
    //         System.out.println("=== INICIO UPLOAD PRODUCT WITH IMAGE ===");
    //         System.out.println("Product ID: " + productId);
    //         System.out.println("File name: " + (file != null ? file.getOriginalFilename() : "null"));
    //         System.out.println("File size: " + (file != null ? file.getSize() : "null"));
    //         System.out.println("Content type: " + (file != null ? file.getContentType() : "null"));
    //         System.out.println("Is empty: " + (file != null ? file.isEmpty() : "null"));
    //         System.out.println("=== FIN UPLOAD PRODUCT WITH IMAGE ===");

    //         // // Validaciones básicas
    //         // if (file == null || file.isEmpty()) {
    //         //     return new GenericResponse(null, "No file provided or file is empty");
    //         // }

    //         // Aquí iría tu lógica de negocio
    //         // Product product = new Product();
    //         // product.setDescription(productDTO.getDescription());
    //         // Category category = new Category();
    //         // category.setId(productDTO.getCategoryId());
    //         // product.setCategory(category);
    //         // productService.saveProductWithImage(product, file);

    //         return new GenericResponse(null, "Product uploaded successfully");
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //         return new GenericResponse(null, "Failed to upload product: " + e.getMessage());
    //     }
    // }

    // /**
    //  * Obtener todas las imágenes de un producto
    //  */
    // @GetMapping("/{productId}")
    // public ResponseEntity<List<ProductImage>> getProductImages(@PathVariable Long productId) {
    //     List<ProductImage> images = productImageService.getImagesByProductId(productId);
    //     return ResponseEntity.ok(images);
    // }

    // /**
    //  * Eliminar una imagen específica
    //  */
    // @DeleteMapping("/images/{imageId}")
    // public ResponseEntity<GenericResponse> deleteImage(@PathVariable Long imageId) {
    //     try {
    //         GenericResponse response = productImageService.deleteImage(imageId);
    //         return ResponseEntity.ok(response);
    //     } catch (Exception e) {
    //         return ResponseEntity.badRequest()
    //                 .body(new GenericResponse(null, "Error al eliminar imagen: " + e.getMessage()));
    //     }
    // }

    // /**
    //  * Eliminar todas las imágenes de un producto
    //  */
    // @DeleteMapping("/{productId}/images")
    // public ResponseEntity<GenericResponse> deleteAllProductImages(@PathVariable Long productId) {
    //     try {
    //         GenericResponse response = productImageService.deleteAllProductImages(productId);
    //         return ResponseEntity.ok(response);
    //     } catch (Exception e) {
    //         return ResponseEntity.badRequest()
    //                 .body(new GenericResponse(null, "Error al eliminar imágenes: " + e.getMessage()));
    //     }
    // }

}