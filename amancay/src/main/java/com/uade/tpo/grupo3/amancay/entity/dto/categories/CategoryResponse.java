package com.uade.tpo.grupo3.amancay.entity.dto.categories;

import lombok.Data;

@Data
public class CategoryResponse {
    private Long id;

    public CategoryResponse(Long id){
      this.id = id;
    }

}
