package com.aegis.inventory.service;

import com.aegis.inventory.entity.Category;

import java.util.List;
import java.util.UUID;

public interface CategoryService {
    List<Category> findActiveCategories();
    Category findCategoryById(UUID id);
    Category createCategory(Category category);
    Category updateCategory(UUID id, Category category);
    void deleteCategory(UUID id);
}
