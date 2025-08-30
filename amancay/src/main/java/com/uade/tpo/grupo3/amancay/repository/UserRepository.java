package com.uade.tpo.grupo3.amancay.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.uade.tpo.grupo3.amancay.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Búsqueda por email con JPQL (similar a findByDescription en Category)
    @Query("select u from User u where u.email = ?1")
    Optional<User> findByEmail(String email);

    // Útil para validar duplicados de email antes de crear/actualizar
    boolean existsByEmail(String email);
}
