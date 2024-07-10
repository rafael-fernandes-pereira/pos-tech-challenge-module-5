package com.github.rafaelfernandes.products.controller;

import java.util.UUID;


public record ProductResponse(
    
    UUID id,

    String name,
    
    Integer amount,
    
    Double price,
    
    
    String image_url

) {
} 
