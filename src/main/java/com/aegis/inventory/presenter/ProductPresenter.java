package com.aegis.inventory.presenter;

import com.aegis.inventory.entity.Product;
import com.aegis.inventory.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class ProductPresenter {
    @Autowired
    private ProductServiceImpl productService;

    @GetMapping("/products")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Object> getProducts() {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("data", productService.findActiveProducts());
        return response;
    }

    @GetMapping("/products/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Object> getProductById(@PathVariable UUID id) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("data", productService.findProductById(id));
        return response;
    }

    @PostMapping("/products")
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, Object> createProduct(@RequestBody Product product) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("data", productService.createProduct(product));
        return response;
    }

    @PutMapping("/products/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Object> updateProduct(@PathVariable UUID id, @RequestBody Product payload) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("data", productService.updateProduct(id, payload));
        return response;
    }

    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable UUID id) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        productService.deleteProduct(id);
    }
}
