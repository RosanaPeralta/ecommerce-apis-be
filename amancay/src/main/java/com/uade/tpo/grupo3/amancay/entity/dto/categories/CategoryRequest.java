package com.uade.tpo.grupo3.amancay.entity.dto.categories;

import lombok.Data;

@Data
public class CategoryRequest {
  private String name;
  private String description;

  public CategoryRequest(String name, String description) {
    this.name = name;
    this.description = description;
  }
}
