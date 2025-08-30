package com.uade.tpo.grupo3.amancay.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Product {

    public Product(){
    };

    public Product(String description, String name, int stock, Double price, String status, 
            String imageUrl, Category category, Activity activity, Discount discount) {
        this.description = description;
        this.name = name;
        this.stock = stock;
        this.price = price;
        this.status = status;
        this.imageUrl = imageUrl;
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
    @Column
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "activity_id")
    private Activity activity;
    
    @OneToOne
    @JoinColumn(name = "discount_id")
    private Discount discount;
}
