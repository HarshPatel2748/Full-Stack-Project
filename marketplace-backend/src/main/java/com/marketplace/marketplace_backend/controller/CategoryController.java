package com.marketplace.marketplace_backend.controller;

import com.marketplace.marketplace_backend.dto.CategoryDTO;
import com.marketplace.marketplace_backend.entity.Category;
import com.marketplace.marketplace_backend.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    //Add Category
    @PostMapping
    public ResponseEntity<Category> addCategory(@RequestBody CategoryDTO dto) {
        return ResponseEntity.ok(categoryService.addCategory(dto));
    }

    //Update Category
    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(
            @PathVariable("id") Long id,
            @RequestBody CategoryDTO dto){
        return ResponseEntity.ok(categoryService.updateCategory(id, dto));
    }

    //Delete Category
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(
            @PathVariable("id") Long id){
        categoryService.deleteCategory(id);
        return ResponseEntity.ok("Category deleted successfully");
    }

    //Get All Categories
    @GetMapping
    public ResponseEntity<List<Category>> getAllCategory(){
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    //Get Category by ID
    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(
            @PathVariable("id") Long id){
        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }
}
