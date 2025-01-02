package com.aegis.inventory.presenter;

import com.aegis.inventory.entity.Category;
import com.aegis.inventory.service.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class CategoryPresenter {
    @Autowired
    private CategoryServiceImpl categoryService;

    @GetMapping("/categories")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Object> getCategories() {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("data", categoryService.findActiveCategories());
        return response;
    }

    @GetMapping("/categories/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Object> getCategoryById(@PathVariable UUID id) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("data", categoryService.findCategoryById(id));
        return response;
    }

    @PostMapping("/categories")
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, Object> createCategory(@RequestBody Category category) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("data", categoryService.createCategory(category));
        return response;
    }

    @PutMapping("/categories/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Object> updateCategory(@PathVariable UUID id, @RequestBody Category payload) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("data", categoryService.updateCategory(id, payload));
        return response;
    }

    @DeleteMapping("/categories/{id}")
    public void deleteCategory(@PathVariable UUID id) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        categoryService.deleteCategory(id);
    }
}
