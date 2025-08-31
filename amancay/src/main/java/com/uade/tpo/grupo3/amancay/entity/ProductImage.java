package com.uade.tpo.grupo3.amancay.entity;

import jakarta.persistence.*;
import lombok.Data;
// import lombok.EqualsAndHashCode;
// import lombok.ToString;

@Entity
@Table(name = "product_images")
@Data
// @EqualsAndHashCode(exclude = "product")
// @ToString(exclude = "product")
public class ProductImage {

    public ProductImage() {
    }

    public ProductImage(String name, String type, byte[] imageData) {
        this.name = name;
        this.type = type;
        this.imageData = imageData;
        // this.product = product;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false)
    private String type;

    @Lob // Para imágenes grandes
    @Column(name = "imagedata", nullable = false)
    private byte[] imageData;

    // @Column(name = "file_size")
    // private Long fileSize;

    // @Column(name = "upload_date")
    // @Temporal(TemporalType.TIMESTAMP)
    // private java.util.Date uploadDate;

    // Relación con Product
    // @ManyToOne(fetch = FetchType.LAZY)
    // @JoinColumn(name = "product_id", nullable = false)
    // private Product product;

    // @PrePersist
    // protected void onCreate() {
    //     uploadDate = new java.util.Date();
    //     if (imageData != null) {
    //         fileSize = (long) imageData.length;
    //     }
    // }
}