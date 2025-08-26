package com.uade.tpo.grupo3.amancay.entity.dto.categories;

import com.uade.tpo.grupo3.amancay.entity.dto.common.GenericResponse;

import lombok.Data;

//TODO: BORRAR CLASE
@Data
public class CategoryResponse extends GenericResponse{

  //Si hiciera falta aca agregariamos mas campos para retornar. Por ahora no hace falta.
    public CategoryResponse(Long id, String message){      
      super(id, message);
    }
}
