package com.uade.tpo.grupo3.amancay.service.productImages;


import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.uade.tpo.grupo3.amancay.entity.dto.common.GenericResponse;
import com.uade.tpo.grupo3.amancay.entity.dto.productImages.ProductImagesResponse;

public interface ProductImageService {
    List<ProductImagesResponse> uploadImages(Long productId, MultipartFile[] files);

    List<ProductImagesResponse> getImagesByProductId(Long productId);

    GenericResponse deleteAllProductImages(Long productId);

    List<ProductImagesResponse> updateImages(Long productId, MultipartFile[] files);
}
