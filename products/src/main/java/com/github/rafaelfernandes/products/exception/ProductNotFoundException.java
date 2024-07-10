package com.github.rafaelfernandes.products.exception;

public class ProductNotFoundException extends RuntimeException {

    private final int status;

    private final String message;

    public ProductNotFoundException(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

}
