package com.uade.tpo.grupo3.amancay.entity;

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
            Category category, Activity activity, Discount discount) {
        this();
        this.description = description;
        this.name = name;
        this.stock = stock;
        this.price = price;
        this.status = status;
        this.category = category;
        this.activity = activity;
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

    @ManyToOne
    @JoinColumn(name = "activity_id")
    private Activity activity;

    @OneToOne
    @JoinColumn(name = "discount_id")
    private Discount discount;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    private List<ProductImage> images;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = jakarta.persistence.FetchType.LAZY)
    private List<Review> reviews;
}
