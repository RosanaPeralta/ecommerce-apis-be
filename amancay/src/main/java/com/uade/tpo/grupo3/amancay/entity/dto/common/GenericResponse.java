package com.uade.tpo.grupo3.amancay.entity.dto.common;

import lombok.Data;

@Data
public class GenericResponse {
    private Long id;
    private String message;

    public GenericResponse(Long id, String message){
      this.id = id;
      this.message = message;
    }
}
