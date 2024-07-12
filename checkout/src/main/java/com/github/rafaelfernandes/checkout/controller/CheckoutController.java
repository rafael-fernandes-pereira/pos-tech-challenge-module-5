package com.github.rafaelfernandes.checkout.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/checkout")
@AllArgsConstructor
public class CheckoutController {

    @PostMapping("/")
    public ResponseEntity<CheckoutResponse> checkout(@RequestBody CheckoutRequest request){

        var response = new CheckoutResponse(
                UUID.randomUUID(),
                request.type()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

}
