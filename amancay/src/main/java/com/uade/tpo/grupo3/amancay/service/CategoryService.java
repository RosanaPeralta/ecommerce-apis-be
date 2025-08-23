
package com.uade.tpo.grupo3.amancay.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.uade.tpo.grupo3.amancay.entity.Category;
import com.uade.tpo.grupo3.amancay.exceptions.DuplicateException;

public interface CategoryService {
    public Page<Category> getCategories(PageRequest pageRequest);

    public Optional<Category> getCategoryById(Long categoryId);

    public Category createCategory(String description) throws DuplicateException;

    public void deleteCategory(Long categoryId);

    public void updateCategory(Long categoryId);
}