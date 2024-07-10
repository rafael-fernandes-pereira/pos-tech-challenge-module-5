package com.github.rafaelfernandes.products.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.rafaelfernandes.products.entity.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, UUID>{

    
} 
