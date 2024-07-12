package com.github.rafaelfernandes.payment.controller;

import com.github.rafaelfernandes.payment.service.PaymentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/payment")
@AllArgsConstructor
public class PaymentController {

    private final PaymentService service;

    @GetMapping("/resume")
    public ResponseEntity<PaymentResumeResponse> getResume(@RequestHeader("userId") UUID userId){

        var resume = this.service.resume(userId);

        return ResponseEntity.ok(resume);

    }

    @PostMapping("/checkout")
    public ResponseEntity<PaymentCheckoutResponse> checkout(@RequestHeader("userId") UUID userId, @RequestBody @Valid PaymentCheckoutRequest request){

        var checkout = this.service.checkout(userId, request);

        return ResponseEntity.ok(checkout);

    }


}
