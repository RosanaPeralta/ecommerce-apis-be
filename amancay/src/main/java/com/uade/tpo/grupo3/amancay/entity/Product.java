package com.uade.tpo.grupo3.amancay.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
// @EqualsAndHashCode(exclude = "images")
// @ToString(exclude = "images")
public class Product {

    public Product() {
        // this.images = new ArrayList<>();
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

    @Column
    private String imageUrl; // Mantener para compatibilidad o imagen principal

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "activity_id")
    private Activity activity;

    @OneToOne
    @JoinColumn(name = "discount_id")
    private Discount discount;

    // Nueva relación con imágenes
    // @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    // private List<ProductImage> images = new ArrayList<>();

    // Métodos de utilidad
    // public void addImage(ProductImage image) {
    //     images.add(image);
    //     image.setProduct(this);
    // }

    // public void removeImage(ProductImage image) {
    //     images.remove(image);
    //     image.setProduct(null);
    // }
}
