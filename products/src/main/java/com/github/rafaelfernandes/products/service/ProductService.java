package com.github.rafaelfernandes.products.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.github.rafaelfernandes.products.controller.ProductRequest;
import com.github.rafaelfernandes.products.controller.ProductResponse;
import com.github.rafaelfernandes.products.entity.ProductEntity;
import com.github.rafaelfernandes.products.exception.ProductNotFoundException;
import com.github.rafaelfernandes.products.repository.ProductRepository;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductService {


    private final ProductRepository repository;

    public UUID createProduct(@Valid ProductRequest productRequest) {
        
        var newProduct = this.toEntity(productRequest);
        newProduct.setId(UUID.randomUUID());

        var productSaved = this.repository.save(newProduct);

        return productSaved.getId();


    }

    private ProductEntity toEntity(ProductRequest request){

        return ProductEntity.builder()
            .name(request.name())
            .price(request.price())
            .amount(request.amount())
            .image_url(request.image_url())
            .build();
    }

    private static ProductResponse toResponse(ProductEntity entity){
        return new ProductResponse(
            entity.getId(), 
            entity.getName(), 
            entity.getAmount(), 
            entity.getPrice(), 
            entity.getImage_url()
        );
    }

    public ProductResponse getProductById(String id) {
        
        var product = this.repository
            .findById(UUID.fromString(id))
            .orElseThrow(() -> 
                new ProductNotFoundException(404,"Product not found")
            );

        return toResponse(product);



    }

    public List<ProductResponse> getAllProducts() {
        
        var products = this.repository.findAll();

        if (products.isEmpty())
            throw new ProductNotFoundException(404, "Products not found");

        return products.stream()
            .map(ProductService::toResponse)
            .toList();
            

    }

    public void deleteProductById(String id) {
        
        this.repository.deleteById(UUID.fromString(id));

    }

    public UUID updateProductById(String id, ProductRequest request){

        var product = this.repository
            .findById(UUID.fromString(id))
            .orElseThrow(() -> 
                new ProductNotFoundException(404,"Product not found")
            );

        product.setAmount(request.amount());
        product.setImage_url(request.image_url());
        product.setName(request.name());
        product.setPrice(request.price());

        var productUpdated = this.repository.save(product);

        return productUpdated.getId();

    }




}
