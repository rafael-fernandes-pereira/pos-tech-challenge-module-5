package com.github.rafaelfernandes.payment.controller;

import com.github.rafaelfernandes.payment.enums.PaymentType;
import jakarta.validation.constraints.NotNull;

public record PaymentCheckoutRequest(

        @NotNull(message = "Type need value")
        PaymentType type

) {
}
