package com.github.rafaelfernandes.carts.exception;

public class CartEmptyException extends RuntimeException{

    private final int status;

    private final String message;


    public CartEmptyException(int status, String message) {
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
