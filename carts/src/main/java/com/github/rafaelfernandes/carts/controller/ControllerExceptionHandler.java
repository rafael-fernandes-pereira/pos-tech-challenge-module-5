package com.github.rafaelfernandes.carts.controller;

import com.github.rafaelfernandes.carts.exception.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ResponseError> productNotFoundException(ProductNotFoundException exception){
        
        var response = new ResponseError(exception.getMessage(), exception.getStatus());

        return ResponseEntity
            .status(exception.getStatus())
            .body(response)
        ;

    }

    @ExceptionHandler(ProductAmountException.class)
    public ResponseEntity<ResponseError> productAmountNotFoundException(ProductAmountException exception){

        var response = new ResponseError(exception.getMessage(), exception.getStatus());

        return ResponseEntity
                .status(exception.getStatus())
                .body(response)
                ;

    }

    @ExceptionHandler(ProductApiException.class)
    public ResponseEntity<ResponseError> productApiException(ProductApiException exception){

        var response = new ResponseError(exception.getMessage(), exception.getStatus());

        return ResponseEntity
                .status(exception.getStatus())
                .body(response)
                ;

    }

    @ExceptionHandler(CartEmptyException.class)
    public ResponseEntity<ResponseError> productApiException(CartEmptyException exception){

        var response = new ResponseError(exception.getMessage(), exception.getStatus());

        return ResponseEntity
                .status(exception.getStatus())
                .body(response)
                ;

    }
}
