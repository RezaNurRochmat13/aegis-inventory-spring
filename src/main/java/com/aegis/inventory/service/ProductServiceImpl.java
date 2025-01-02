package com.aegis.inventory.service;

import com.aegis.inventory.entity.Category;
import com.aegis.inventory.entity.Product;
import com.aegis.inventory.exception.ResourceNotFound;
import com.aegis.inventory.repository.CategoryRepository;
import com.aegis.inventory.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Product> findActiveProducts() {
        return productRepository.findAllActiveProducts();
    }

    @Override
    public Product findProductById(UUID id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFound("Product not found with id + " + id));

        return product;
    }

    @Override
    public Product createProduct(Product payload) {
        return productRepository.save(payload);
    }

    @Override
    public Product updateProduct(UUID id, Product payload) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Product not found with id + " + id));
        Category category = categoryRepository.findById(payload.getCategory().getId())
                .orElseThrow(() -> new ResourceNotFound("Category not found with id + " + payload.getCategory().getId()));

        product.setName(payload.getName());
        product.setDescription(payload.getDescription());
        product.setPrice(payload.getPrice());
        product.setStock(payload.getStock());
        product.setCategory(category);

        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(UUID id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFound("Product not found with id + " + id));
        product.setDeletedAt(LocalDateTime.now());

        productRepository.save(product);
    }
}
