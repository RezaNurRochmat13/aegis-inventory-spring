package com.aegis.inventory.service;

import com.aegis.inventory.entity.Category;
import com.aegis.inventory.exception.ResourceNotFound;
import com.aegis.inventory.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> findActiveCategories() {
        return categoryRepository.findActiveCategories();
    }

    @Override
    public Category findCategoryById(UUID id) {
        return categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFound("Category not found with id + " + id));
    }

    @Override
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(UUID id, Category category) {
        Category categoryById = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFound("Category not found with id + " + id));
        categoryById.setName(category.getName());
        categoryById.setDescription(category.getDescription());

        return categoryRepository.save(categoryById);
    }

    @Override
    public void deleteCategory(UUID id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFound("Category not found with id + " + id));
        category.setDeletedAt(LocalDateTime.now());

        categoryRepository.save(category);
    }
}
