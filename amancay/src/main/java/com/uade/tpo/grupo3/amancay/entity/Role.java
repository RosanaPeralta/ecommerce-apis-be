package com.uade.tpo.grupo3.amancay.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

//En caso de que hagamos una tabla para Vendedor y otra para Cliente.
@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String description;

    @ManyToMany(mappedBy = "roles")
    private List<User> users;

    // @OneToOne
    // private User user;
}
