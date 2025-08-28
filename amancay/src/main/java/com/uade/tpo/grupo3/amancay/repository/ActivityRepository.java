package com.uade.tpo.grupo3.amancay.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.uade.tpo.grupo3.amancay.entity.Activity;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {

    @Query(value = "select a from Activity a where a.description = ?1")
    List<Activity> findByDescription(String description);
}