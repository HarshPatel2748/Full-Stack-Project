package com.marketplace.marketplace_backend.service.impl;

import com.marketplace.marketplace_backend.dto.CategoryDTO;
import com.marketplace.marketplace_backend.entity.Category;
import com.marketplace.marketplace_backend.repository.CategoryRepository;
import com.marketplace.marketplace_backend.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    //Add a new category
    @Override
    public Category addCategory(CategoryDTO dto) {
        Category category = Category.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .build();
        return categoryRepository.save(category);
    }

    //Update an existing category
    @Override
    public Category updateCategory(Long categoryId, CategoryDTO dto) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(()-> new RuntimeException("Category not found"));

        category.setName(dto.getName());
        category.setDescription(dto.getDescription());

        return categoryRepository.save(category);
    }

    //Delete a category
    @Override
    public void deleteCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(()-> new RuntimeException("Category not found"));

        categoryRepository.delete(category);
    }

    //Get all categories
    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    //Get category by ID
    @Override
    public Category getCategoryById(Long categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(()-> new RuntimeException("Category not found"));
    }
}
