package com.github.rafaelfernandes.products.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.rafaelfernandes.products.service.ProductService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {

    private final ProductService service;

    @PostMapping("/")
    public ResponseEntity<UUID> createProduct(@Valid @RequestBody ProductRequest productRequest) {
        var productId = service.createProduct(productRequest);
        return ResponseEntity.ok(productId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable String id) {
        var product = service.getProductById(id);
        return ResponseEntity.ok(product);
    }

    @GetMapping("/")
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        var products = service.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String id) {
        service.deleteProductById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<UUID> updateProduct(@PathVariable String id, @Valid @RequestBody ProductRequest productRequest) {
        var updatedProduct = service.updateProductById(id, productRequest);
        return ResponseEntity.ok(updatedProduct);
    }

    



}
