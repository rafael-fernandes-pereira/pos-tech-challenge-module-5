package com.github.rafaelfernandes.products.exception;

public record ResponseError(
    String message, Integer status
) {
    
}
