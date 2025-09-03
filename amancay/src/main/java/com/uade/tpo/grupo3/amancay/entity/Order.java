package com.uade.tpo.grupo3.amancay.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
public class Order {

    public Order() {
    }

    public Order(Long customerId, String status) {
        this.customerId = customerId;
        this.items = new ArrayList<>();
        this.status = status;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long customerId;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> items = new ArrayList<>();

    @Column(nullable = false)
    private String status;
    
    @Column(name = "total_amount", nullable = false)
    private Double totalAmount;

    @Column(name = "date_of_order", nullable = false)
    private LocalDateTime orderDate;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedDate;

    @Column
    private String notes;

    @PrePersist
    void prePersist() {
      orderDate = LocalDateTime.now();
      updatedDate = orderDate;
      if (totalAmount == null) {
          totalAmount = 0.0;
      }
    }

    @PreUpdate
    void preUpdate() {
      updatedDate = LocalDateTime.now();
    }

    public void addItem(Product product, int quantity, Double unitPrice) {
        OrderItem item = new OrderItem(this, product, quantity, unitPrice);
        items.add(item);
        calculateTotal();
    }

    private void calculateTotal() {
        this.totalAmount = items.stream()
                            .map(i -> i.getSubtotal() == null ? 0.0 : i.getSubtotal())
                            .reduce(0.0, Double::sum);
    }
}