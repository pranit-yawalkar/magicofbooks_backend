package com.magicofbooks.backend.service.impl;

import com.magicofbooks.backend.exception.ResourceNotFoundException;
import com.magicofbooks.backend.model.Category;
import com.magicofbooks.backend.repository.CategoryRepository;
import com.magicofbooks.backend.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategories() {
        return this.categoryRepository.findAll();
    }

    @Override
    public Category getCategoryById(Long categoryId) {
        return this.categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found!"));
    }

    @Override
    public Category createCategory(Category category) {
        category.setCreatedDate(new Date());
        category.setModifiedDate(new Date());
        return this.categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Long categoryId, Category category) {
        Category category1 = this.categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found!"));
        category1.setCategoryId(categoryId);
        category1.setCategoryName(category.getCategoryName());
        category1.setCategoryDesc(category.getCategoryDesc());
        category1.setModifiedDate(new Date());
        return this.categoryRepository.save(category1);
    }

    @Override
    public void deleteCategory(Long categoryId) {
        Category category = this.categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found!"));
        this.categoryRepository.delete(category);
    }
}
