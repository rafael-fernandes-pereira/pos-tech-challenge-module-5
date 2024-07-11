package com.github.rafaelfernandes.carts.controller;

import java.util.UUID;

public record CartResponse(
        UUID productId,
        String productName,
        Integer amount,
        Double priceUnit
) {
}
