package com.github.rafaelfernandes.carts.exception;

public class ProductAmountException extends RuntimeException {

    private final int status;

    private final String message;

    public ProductAmountException(int status, String message) {
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
