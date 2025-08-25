package com.uade.tpo.grupo3.amancay.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uade.tpo.grupo3.amancay.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    
}
