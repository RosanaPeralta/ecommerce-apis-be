package com.uade.tpo.grupo3.amancay.service.categories;

import java.security.InvalidParameterException;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.uade.tpo.grupo3.amancay.entity.Category;
import com.uade.tpo.grupo3.amancay.entity.dto.categories.CategoryRequest;
import com.uade.tpo.grupo3.amancay.entity.dto.common.GenericResponse;

import io.micrometer.core.instrument.config.validate.Validated.Invalid;

public interface CategoryService {
    public Page<Category> getCategories(PageRequest pageRequest);

    public Optional<Category> getCategoryById(Long categoryId);

    public Category createCategory(String name, String description);

    public GenericResponse deleteCategory(Long categoryId) throws InvalidParameterException;

    public GenericResponse updateCategory(Long categoryId, CategoryRequest categoryRequest) throws InvalidParameterException;
}