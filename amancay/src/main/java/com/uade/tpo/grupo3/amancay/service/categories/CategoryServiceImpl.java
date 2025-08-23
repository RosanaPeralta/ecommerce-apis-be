package com.uade.tpo.grupo3.amancay.service.categories;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.uade.tpo.grupo3.amancay.entity.Category;
import com.uade.tpo.grupo3.amancay.entity.dto.categories.CategoryRequest;
import com.uade.tpo.grupo3.amancay.entity.dto.categories.CategoryResponse;
import com.uade.tpo.grupo3.amancay.exceptions.NotFoundException;
import com.uade.tpo.grupo3.amancay.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Page<Category> getCategories(PageRequest pageable) {
        return categoryRepository.findAll(pageable);
    }

    public Optional<Category> getCategoryById(Long categoryId) {
        return categoryRepository.findById(categoryId);
    }

    public Category createCategory(String name, String description) {
      return categoryRepository.save(new Category(name, description));
    }

    public void deleteCategory(Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }

  public CategoryResponse updateCategory(CategoryRequest categoryRequest) {
      Category category = categoryRepository.findById(categoryRequest.getId())
              .orElseThrow(() -> new NotFoundException("Fallo la actualizacion"));

      category.setName(categoryRequest.getName());
      category.setDescription(categoryRequest.getDescription());

      Category updatedCategory = categoryRepository.saveAndFlush(category);

      return new CategoryResponse(updatedCategory.getId());
  }

}
