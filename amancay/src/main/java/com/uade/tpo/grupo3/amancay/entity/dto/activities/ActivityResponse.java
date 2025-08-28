package com.uade.tpo.grupo3.amancay.entity.dto.activities;

import lombok.Data;

@Data
public class ActivityResponse {
    
    private Long id;

    public ActivityResponse(Long id){
        this.id = id;
    }
}
