package com.github.rafaelfernandes.payment.checkout;


import com.github.rafaelfernandes.payment.enums.PaymentType;

import java.util.UUID;

public record CheckoutResponse(

        UUID checkoutId,
        PaymentType paymentType

) {
}
