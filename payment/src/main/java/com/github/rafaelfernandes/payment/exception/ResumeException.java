package com.github.rafaelfernandes.payment.exception;

public class ResumeException extends RuntimeException {

    private final int status;

    private final String message;

    public ResumeException(int status, String message) {
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
