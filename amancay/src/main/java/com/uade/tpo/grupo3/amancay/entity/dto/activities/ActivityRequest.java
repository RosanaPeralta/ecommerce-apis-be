package com.uade.tpo.grupo3.amancay.entity.dto.activities;

import lombok.Data;

@Data
public class ActivityRequest {

    private Long id;
    private String name;
    private String description;

    public ActivityRequest(Long id, String name, String description){
        this.id = id;
        this.name = name;
        this.description = description;
    }    
}
