package com.github.rafaelfernandes.payment.controller;

import com.github.rafaelfernandes.payment.enums.PaymentType;

import java.util.UUID;

public record PaymentCheckoutResponse(
        UUID paymentId,
        UUID userId,
        PaymentType paymentType,
        Double priceFinal
) {
}
