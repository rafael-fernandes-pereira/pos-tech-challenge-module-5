package com.github.rafaelfernandes.payment.controller;

import com.github.rafaelfernandes.payment.cart.CartResponse;

import java.util.List;

public record PaymentResumeResponse(

        List<CartResponse> itens,

        Integer totalItens,

        Double priceFinal


) {
}
