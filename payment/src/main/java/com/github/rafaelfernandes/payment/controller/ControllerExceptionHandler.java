package com.github.rafaelfernandes.payment.controller;

import com.github.rafaelfernandes.payment.exception.CartException;

import com.github.rafaelfernandes.payment.exception.ResponseError;
import com.github.rafaelfernandes.payment.exception.ResumeException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(CartException.class)
    public ResponseEntity<ResponseError> productNotFoundException(CartException exception){
        
        var response = new ResponseError(exception.getMessage(), exception.getStatus());

        return ResponseEntity
            .status(exception.getStatus())
            .body(response)
        ;

    }

    @ExceptionHandler(ResumeException.class)
    public ResponseEntity<ResponseError> productNotFoundException(ResumeException exception){

        var response = new ResponseError(exception.getMessage(), exception.getStatus());

        return ResponseEntity
                .status(exception.getStatus())
                .body(response)
                ;

    }

}
