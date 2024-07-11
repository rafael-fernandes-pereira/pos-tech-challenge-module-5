package com.github.rafaelfernandes.carts.controller;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CartAddRequest(
    
    @NotNull(message = "ProductId cannot be null")
    UUID productId,
    
    @NotNull(message = "Amount cannot be null")
    @Positive(message = "Amount must be positive")
    Integer amount
    
) {

}
