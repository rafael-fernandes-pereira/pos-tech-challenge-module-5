package com.github.rafaelfernandes.checkout.controller;

import com.github.rafaelfernandes.checkout.enums.PaymentType;

import java.util.UUID;

public record CheckoutResponse(

        UUID checkoutId,
        PaymentType paymentType

) {
}
