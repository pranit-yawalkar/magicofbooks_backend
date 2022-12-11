package com.magicofbooks.backend.service;

import com.magicofbooks.backend.model.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategories();
    Category getCategoryById(Long categoryId);
    Category createCategory(Category category);
    Category updateCategory(Long categoryId, Category category);
    void deleteCategory(Long categoryId);
}
