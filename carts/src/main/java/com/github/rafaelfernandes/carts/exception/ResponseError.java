package com.github.rafaelfernandes.carts.exception;

public record ResponseError(
    String message, Integer status
) {
    
}
