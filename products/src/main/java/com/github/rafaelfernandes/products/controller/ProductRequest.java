package com.github.rafaelfernandes.products.controller;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ProductRequest(
    @NotBlank(message = "Name cannot be null")
    String name,
    
    @NotNull(message = "Amount cannot be null")
    @Positive(message = "Amount must be positive")
    Integer amount,
    
    @NotNull(message = "Price cannot be null")
    @Positive(message = "Price must be positive")
    Double price,
    
    @NotBlank(message = "Image cannot be null")
    String image_url

) {
} 
