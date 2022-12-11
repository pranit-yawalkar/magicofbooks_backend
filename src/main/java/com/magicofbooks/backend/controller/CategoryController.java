package com.magicofbooks.backend.controller;

import com.magicofbooks.backend.dto.ResponseDTO;
import com.magicofbooks.backend.model.Category;
import com.magicofbooks.backend.service.impl.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryServiceImpl categoryService;

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories = this.categoryService.getAllCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long categoryId) {
        Category category = this.categoryService.getCategoryById(categoryId);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        Category category1 = this.categoryService.createCategory(category);
        return new ResponseEntity<>(category1, HttpStatus.CREATED);
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long categoryId, @RequestBody Category category) {
        Category updateCategory = this.categoryService.updateCategory(categoryId, category);
        return new ResponseEntity<>(updateCategory, HttpStatus.OK);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<ResponseDTO> deleteCategory(@PathVariable Long categoryId) {
        this.categoryService.deleteCategory(categoryId);
        ResponseDTO responseDTO = new ResponseDTO(true, "Category deleted successfully!");
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}
