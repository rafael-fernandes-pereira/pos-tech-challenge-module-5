package com.github.rafaelfernandes.carts.product;

import lombok.*;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductApi {
    
    UUID id;

    String name;
    
    Integer amount;
    
    Double price;

}
