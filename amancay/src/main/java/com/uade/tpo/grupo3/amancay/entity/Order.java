package com.uade.tpo.grupo3.amancay.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Data
public class Order {

    public Order() {
    }

    public Order(Product product, Long customerId, Long productId, int quantity, float price, String status){
        this.customerId = customerId;
        this.product = product;
        this.product.setId(productId);
        this.quantity = quantity;
        this.price = price;
        this.status = status;
        this.orderDate = LocalDateTime.now();
        this.updatedDate = LocalDateTime.now();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long customerId;

    @ManyToOne()
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private String status;
    
    @Column(nullable = false)
    private float price;

    @Column(name = "date_of_order", nullable = false)
    private LocalDateTime orderDate;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedDate;

    @Column
    private String notes;


}
