package com.uade.tpo.grupo3.amancay.service.product_images;

import org.springframework.web.multipart.MultipartFile;
import com.uade.tpo.grupo3.amancay.entity.ProductImage;
import com.uade.tpo.grupo3.amancay.entity.dto.common.GenericResponse;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uade.tpo.grupo3.amancay.entity.Product;
import com.uade.tpo.grupo3.amancay.repository.ProductRepository;
import com.uade.tpo.grupo3.amancay.repository.ProductImageRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

@Service
@Transactional
public class ProductImageServiceImpl implements ProductImageService {

    @Autowired
    private ProductImageRepository productImageRepository;

    @Autowired
    private ProductRepository productRepository;

    // Allowed file types
    private static final List<String> ALLOWED_TYPES = Arrays.asList(
            "image/jpeg", "image/jpg", "image/png", "image/webp");


    // @Override
    // public List<ProductImage> uploadImages(Long productId, MultipartFile[] files) {
    //     // Verify if the product exists
    //     Product product = productRepository.findById(productId)
    //             .orElseThrow(() -> new RuntimeException("Product not found with id: " + productId));

    //     System.out.println("Product found with id: " + product.getId());
    //     List<ProductImage> uploadedImages = new ArrayList<>();

    //     for (MultipartFile file : files) {
    //         try {
    //             // Validations for the file
    //             validateFile(file);

    //             ProductImage productImage = new ProductImage();
    //             productImage.setName(file.getOriginalFilename());
    //             productImage.setType(file.getContentType());
    //             productImage.setImageData(file.getBytes());
    //             // productImage.setProduct(product);

    //             ProductImage savedImage = productImageRepository.save(productImage);
    //             uploadedImages.add(savedImage);

    //         } catch (IOException e) {
    //             throw new RuntimeException("Error processing the file: " + file.getOriginalFilename(), e);
    //         }
    //     }

    //     return uploadedImages;
    // }

    // // @Override
    // // public List<ProductImage> getImagesByProductId(Long productId) {
    // //     return productImageRepository.findByProductId(productId);
    // // }

    // @Override
    // public GenericResponse deleteImage(Long imageId) {
    //     if (!productImageRepository.existsById(imageId)) {
    //         throw new RuntimeException("Image not found with id: " + imageId);
    //     }

    //     productImageRepository.deleteById(imageId);
    //     return new GenericResponse(imageId, "Image deleted successfully");
    // }

    // // @Override
    // // public GenericResponse deleteAllProductImages(Long productId) {
    // //     List<ProductImage> images = productImageRepository.findByProductId(productId);
    // //     productImageRepository.deleteAll(images);
    // //     return new GenericResponse(productId, "All product images deleted successfully");
    // // }

    // @Override
    // public byte[] getImageData(Long imageId) {
    //     ProductImage image = productImageRepository.findById(imageId)
    //             .orElseThrow(() -> new RuntimeException("Image not found with id: " + imageId));
    //     return image.getImageData();
    // }

    // private void validateFile(MultipartFile file) {
    //     if (file.isEmpty()) {
    //         throw new RuntimeException("The file is empty");
    //     }

    //     if (!ALLOWED_TYPES.contains(file.getContentType())) {
    //         throw new RuntimeException("File type not allowed. Only allowed types: " +
    //                 String.join(", ", ALLOWED_TYPES));
    //     }
    // }
}
