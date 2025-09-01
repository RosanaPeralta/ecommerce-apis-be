package com.uade.tpo.grupo3.amancay.entity.dto.activities;

import lombok.Data;

@Data
public class ActivityRequest {

    private String name;
    private String description;

    public ActivityRequest(String name, String description){
        this.name = name;
        this.description = description;
    }    
}