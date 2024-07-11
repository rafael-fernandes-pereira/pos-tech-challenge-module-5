package com.github.rafaelfernandes.carts.controller;

import com.github.rafaelfernandes.carts.service.CartService;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;


@RestController
@RequestMapping("/cart")
@AllArgsConstructor
public class CartController {


    private final CartService service;

    @PostMapping("/add")
    public ResponseEntity<Boolean> addItem(
        @RequestHeader("userId") UUID userId, 
        @RequestBody @Valid CartAddRequest request) {
            
        var status = service.addProduct(userId, request);

        return ResponseEntity.ok(status);
    }

    @GetMapping("/list")
    public ResponseEntity<List<CartResponse>> getCartItens(@RequestHeader("userId") UUID userId) {

        var productsInCart = this.service.getAllItens(userId);

        return ResponseEntity.ok(productsInCart);

    }

    @DeleteMapping("/remove/{productId}")
    public ResponseEntity<Boolean> removeItem(@RequestHeader("userId") UUID userId, @PathVariable UUID productId){

        var status = service.removeItem(userId, productId);

        return ResponseEntity.ok(status);

    }
    

}
