package com.uade.tpo.grupo3.amancay.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.uade.tpo.grupo3.amancay.entity.Discount;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Long> {

    @Query("SELECT d FROM Discount d WHERE LOWER(d.description) LIKE LOWER(CONCAT('%', :description, '%'))")
    List<Discount> findByDescriptionPartial(@Param("description") String description);

}
