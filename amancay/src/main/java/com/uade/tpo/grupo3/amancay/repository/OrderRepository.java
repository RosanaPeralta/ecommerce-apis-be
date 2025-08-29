package com.uade.tpo.grupo3.amancay.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.uade.tpo.grupo3.amancay.entity.Order;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    
    List<Order> findByCustomerId(Long customerId);
    
    List<Order> findByStatus(String status);
    
    List<Order> findByProductId(Long productId);
}
