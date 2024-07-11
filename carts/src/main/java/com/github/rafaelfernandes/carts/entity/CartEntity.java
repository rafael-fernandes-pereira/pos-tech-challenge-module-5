package com.github.rafaelfernandes.carts.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.UUID;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_cart")
public class CartEntity {

    @Id
    UUID id;

    UUID userId;

    UUID productId;

    String productName;

    private Integer amount;

    private Double priceUnit;



}
