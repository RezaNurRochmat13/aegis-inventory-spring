package com.aegis.inventory.service;

import com.aegis.inventory.entity.Product;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    List<Product> findActiveProducts();
    Product findProductById(UUID id);
    Product createProduct(Product product);
    Product updateProduct(UUID id, Product product);
    void deleteProduct(UUID id);
}
