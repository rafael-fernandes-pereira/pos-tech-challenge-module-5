package com.github.rafaelfernandes.payment.cart;

import java.util.UUID;

public record CartResponse(
        UUID productId,
        String productName,
        Integer amount,
        Double priceUnit
) {
}
