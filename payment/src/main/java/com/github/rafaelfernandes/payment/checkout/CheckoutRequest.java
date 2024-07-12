package com.github.rafaelfernandes.payment.checkout;

import com.github.rafaelfernandes.payment.enums.PaymentType;

public record CheckoutRequest(

        Double priceFinal,
        PaymentType type

) {
}
