package com.uade.tpo.grupo3.amancay.entity;

import java.util.List;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Product {

    public Product() {
        this.images = new ArrayList<>();
    }

    public Product(String description, String name, int stock, Double price, String status, 
            String imageUrl, Category category, List<Activity> activities, Discount discount) {
        this.description = description;
        this.name = name;
        this.stock = stock;
        this.price = price;
        this.status = status;
        this.category = category;
        this.activities = activities;
        this.discount = discount;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String description;

    @Column
    private String name;

    @Column
    private int stock;

    @Column
    private Double price;

    @Column
    private String status;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToMany
    @JoinTable(name = "product_activity", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "activity_id"))
    private List<Activity> activities;
    
    @OneToOne
    @JoinColumn(name = "discount_id")
    private Discount discount;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    private List<ProductImage> images;
}
