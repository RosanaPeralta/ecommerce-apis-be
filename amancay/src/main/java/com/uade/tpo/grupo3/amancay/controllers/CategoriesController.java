package com.uade.tpo.grupo3.amancay.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uade.tpo.grupo3.amancay.entity.Category;
import com.uade.tpo.grupo3.amancay.entity.dto.categories.CategoryRequest;
import com.uade.tpo.grupo3.amancay.entity.dto.categories.CategoryResponse;
import com.uade.tpo.grupo3.amancay.entity.dto.common.GenericResponse;
import com.uade.tpo.grupo3.amancay.exceptions.DuplicateException;
import com.uade.tpo.grupo3.amancay.service.categories.CategoryService;

import java.net.URI;
import java.security.InvalidParameterException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("categories")
public class CategoriesController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping // GET /categories
    public ResponseEntity<Page<Category>> getCategories(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size) {
        if (page == null || size == null)
            return ResponseEntity.ok(categoryService.getCategories(PageRequest.of(0, Integer.MAX_VALUE)));
        return ResponseEntity.ok(categoryService.getCategories(PageRequest.of(page, size)));
    }

    @GetMapping("/{categoryId}") // GET /categories/{categoryId}
    public ResponseEntity<Category> getCategoryById(@PathVariable Long categoryId) {
        Optional<Category> result = categoryService.getCategoryById(categoryId);
        if (result.isPresent())
            return ResponseEntity.ok(result.get());

        return ResponseEntity.noContent().build();
    }

    @PostMapping // POST /categories
    public ResponseEntity<Object> createCategory(@RequestBody CategoryRequest request) throws DuplicateException {
        Category result = categoryService.createCategory(request.getName(), request.getDescription());
        return ResponseEntity.created(URI.create("/categories/" + result.getId())).body(result);
    }

    @PutMapping("/{categoryId}") // PUT /categories/{categoryId}
    public ResponseEntity<GenericResponse> updateCategory(@PathVariable Long categoryId,
            @RequestBody CategoryRequest categoryRequest) throws InvalidParameterException {
        GenericResponse result = categoryService.updateCategory(categoryId, categoryRequest);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{categoryId}") // DELETE /categories/{categoryId}
    public ResponseEntity<GenericResponse> deleteCategory(@PathVariable Long categoryId)
            throws InvalidParameterException {
        GenericResponse result = categoryService.deleteCategory(categoryId);
        return ResponseEntity.ok(result);
    }
}
