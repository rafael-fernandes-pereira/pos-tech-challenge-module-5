package com.github.rafaelfernandes.payment.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.rafaelfernandes.payment.cart.CartResponse;
import com.github.rafaelfernandes.payment.checkout.CheckoutRequest;
import com.github.rafaelfernandes.payment.checkout.CheckoutResponse;
import com.github.rafaelfernandes.payment.controller.PaymentCheckoutRequest;
import com.github.rafaelfernandes.payment.controller.PaymentCheckoutResponse;
import com.github.rafaelfernandes.payment.controller.PaymentResumeResponse;
import com.github.rafaelfernandes.payment.entity.PaymentEntity;
import com.github.rafaelfernandes.payment.exception.CartException;
import com.github.rafaelfernandes.payment.exception.ResumeException;
import com.github.rafaelfernandes.payment.repository.PaymentRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class PaymentService {

    private final RestTemplate restTemplate;

    private final PaymentRepository repository;

    public PaymentResumeResponse resume(UUID userId){

        try {

            var headers = new HttpHeaders();
            headers.set("Accept", "application/json");
            headers.set("userId", userId.toString());

            var entity = new HttpEntity<>(headers);

            var url = "http://localhost:8082/cart/list";

            var response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

            var mapper = new ObjectMapper();
            
            var products = mapper.readValue(response.getBody(), new TypeReference<List<CartResponse>>() {});

            Double priceFinal = products.stream()
                    .mapToDouble(product -> product.amount() * product.priceUnit())
                    .sum();

            return new PaymentResumeResponse(
                    products,
                    products.size(),
                    priceFinal
            );

        } catch (HttpClientErrorException e ){

            throw new CartException(e.getStatusCode().value(), e.getMessage());

        } catch (JsonProcessingException e) {
            throw new ResumeException(500, e.getMessage());
        }

    }

    public PaymentCheckoutResponse checkout(UUID userId, PaymentCheckoutRequest request) {

        try {

            var resume = this.resume(userId);

            var checkoutRequest = new CheckoutRequest(resume.priceFinal(), request.type());

            var checkoutRequestSend = new HttpEntity<>(checkoutRequest);

            var urlCheckout = "http://localhost:8094/checkout/";

            var responseCheckout = restTemplate.exchange(urlCheckout, HttpMethod.POST, checkoutRequestSend, String.class);

            var mapper = new ObjectMapper();

            var checkoutResponse = mapper.readValue(responseCheckout.getBody(), new TypeReference<CheckoutResponse>() {});

            var entityToSave = new PaymentEntity(
                    UUID.randomUUID(),
                    userId,
                    checkoutResponse.checkoutId(),
                    resume.priceFinal()
            );

            var entitySaved = this.repository.save(entityToSave);

            return new PaymentCheckoutResponse(
                    entitySaved.getId(),
                    userId,
                    request.type(),
                    resume.priceFinal()
            );



        } catch (Exception e){
            throw new CartException(500, e.getMessage());
        }



    }

}
