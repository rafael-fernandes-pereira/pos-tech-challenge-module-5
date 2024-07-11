package com.github.rafaelfernandes.carts.service;

import com.github.rafaelfernandes.carts.controller.CartAddRequest;
import com.github.rafaelfernandes.carts.controller.CartResponse;
import com.github.rafaelfernandes.carts.entity.CartEntity;
import com.github.rafaelfernandes.carts.exception.CartEmptyException;
import com.github.rafaelfernandes.carts.exception.ProductAmountException;
import com.github.rafaelfernandes.carts.exception.ProductApiException;
import com.github.rafaelfernandes.carts.exception.ProductNotFoundException;
import com.github.rafaelfernandes.carts.product.ProductApi;
import com.github.rafaelfernandes.carts.repository.CartRepository;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;


import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CartService {

    private final RestTemplate restTemplate;
    private final CartRepository repository;


    public Boolean addProduct(UUID userId, CartAddRequest request){

        try {

            var productInCart = this.repository.findByUserIdAndProductId(userId, request.productId());

            var url = "http://localhost:8081/products/"+ request.productId();

            var productApi = restTemplate.getForObject(url, ProductApi.class);

            if (productApi == null)
                throw new ProductNotFoundException(404, "Produto nao encontrado");

            if (request.amount() > productApi.getAmount())
                throw new ProductAmountException(404, "Quantidade excedida!");

            if (productInCart != null && (request.amount() + productInCart.getAmount()) > productApi.getAmount())
                throw new ProductAmountException(404, "Quantidade excedida!");


            var addProductCart = CartEntity.builder()
                    .id(UUID.randomUUID())
                    .productId(productApi.getId())
                    .productName(productApi.getName())
                    .amount(request.amount())
                    .priceUnit(productApi.getPrice())
                    .userId(userId)
                    .build();

            if (productInCart != null){
                addProductCart.setId(productInCart.getId());
            }

            this.repository.save(addProductCart);

            return Boolean.TRUE;

        } catch (HttpClientErrorException e){
          throw new ProductApiException(e.getStatusCode().value(), e.getMessage());
        }

    }

    public List<CartResponse> getAllItens(UUID userId){

        var products = this.repository.findAllByUserId(userId);

        if (products == null || products.isEmpty())
            throw new CartEmptyException(404, "Carrinho Vazio!");

        return products.stream()
                .map(CartService::toResponse)
                .toList();


    }

    private static CartResponse toResponse(CartEntity entity){
        return new CartResponse(
                entity.getProductId(),
                entity.getProductName(),
                entity.getAmount(),
                entity.getPriceUnit()
        );
    }

    public Boolean removeItem(UUID userId, UUID productId){

        var productInCart = this.repository.findByUserIdAndProductId(userId, productId);

        if (productInCart == null)
            throw new CartEmptyException(404, "Carrinho Vazio!");

        this.repository.delete(productInCart);

        return Boolean.TRUE;

    }

}
