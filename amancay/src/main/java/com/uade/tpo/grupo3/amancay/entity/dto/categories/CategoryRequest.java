package com.uade.tpo.grupo3.amancay.entity.dto.categories;

import lombok.Data;

@Data
public class CategoryRequest {
    private Long id;
    private String name;
    private String description;

    public CategoryRequest(Long id, String name, String description){
      this.id = id;
      this.name = name;
      this.description = description;
    }
}
