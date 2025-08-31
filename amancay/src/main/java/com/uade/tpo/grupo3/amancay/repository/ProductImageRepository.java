// ProductImageRepository.java
package com.uade.tpo.grupo3.amancay.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.uade.tpo.grupo3.amancay.entity.ProductImage;
import java.util.List;

@Repository
public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {
    // List<ProductImage> findByProductId(Long productId);

    // void deleteByProductId(Long productId);
}