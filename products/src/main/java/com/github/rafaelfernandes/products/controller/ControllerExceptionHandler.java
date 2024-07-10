package com.github.rafaelfernandes.products.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.github.rafaelfernandes.products.exception.ProductNotFoundException;
import com.github.rafaelfernandes.products.exception.ResponseError;


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
}
