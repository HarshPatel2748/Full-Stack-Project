package com.marketplace.marketplace_backend.service;

import com.marketplace.marketplace_backend.dto.CategoryDTO;
import com.marketplace.marketplace_backend.entity.Category;

import java.util.List;

public interface CategoryService {

    Category addCategory(CategoryDTO dto);

    Category updateCategory(Long categoryId, CategoryDTO dto);

    void deleteCategory(Long categoryId);

    List<Category> getAllCategories();

    Category getCategoryById(Long categoryId);

}
