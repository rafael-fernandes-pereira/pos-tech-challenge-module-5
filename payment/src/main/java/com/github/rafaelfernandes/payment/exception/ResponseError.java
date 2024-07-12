package com.github.rafaelfernandes.payment.exception;

public record ResponseError(
    String message, Integer status
) {
    
}
