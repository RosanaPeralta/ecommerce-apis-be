package com.uade.tpo.grupo3.amancay.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import com.uade.tpo.grupo3.amancay.entity.Order;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    
    List<Order> findByCustomerId(Long customerId);
    Page<Order> findByCustomerId(Long customerId, Pageable pageable);
    
    List<Order> findByStatus(String status);
    Page<Order> findByStatus(String status, Pageable pageable);
    
    List<Order> findByItemsProductId(Long productId);
    Page<Order> findByItemsProductId(Long productId, Pageable pageable);
}
