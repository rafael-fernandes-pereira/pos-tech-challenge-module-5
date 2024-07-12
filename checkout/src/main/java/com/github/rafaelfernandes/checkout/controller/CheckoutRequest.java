package com.github.rafaelfernandes.checkout.controller;

import com.github.rafaelfernandes.checkout.enums.PaymentType;

public record CheckoutRequest(

        Double priceFinal,
        PaymentType type

) {
}
