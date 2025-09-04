package com.uade.tpo.grupo3.amancay.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;

import com.uade.tpo.grupo3.amancay.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT DISTINCT p FROM Product p LEFT JOIN p.activities a WHERE " +
            "(:categoryId IS NULL OR p.category.id = :categoryId) AND " +
            "(:activityId IS NULL OR a.id = :activityId) AND " +
            "(:minPrice IS NULL OR p.price >= :minPrice) AND " +
            "(:maxPrice IS NULL OR p.price <= :maxPrice) AND " +
            "(:withStock = false OR p.stock > 0)")
    List<Product> findByFilters(@Param("categoryId") Long categoryId,
            @Param("activityId") Long activityId,
            @Param("minPrice") Double minPrice,
            @Param("maxPrice") Double maxPrice,
            @Param("withStock") boolean withStock,
            Pageable pageable);

}