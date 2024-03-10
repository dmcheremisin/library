package com.dmch.math.controller;

import com.dmch.math.BaseTest;
import com.dmch.math.dto.InputFailedValidationResponse;
import com.dmch.math.dto.Response;
import com.dmch.math.exception.InputValidationException;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class BadRequestTest extends BaseTest {

    @Test
    public void badRequestTest() {
        Mono<Response> response = webClient
                .get()
                .uri("reactive-math/square/{number}/throw", 5)
                .retrieve()
                .bodyToMono(Response.class)
                .doOnNext(System.out::println);

        StepVerifier.create(response)
                .verifyError(WebClientResponseException.BadRequest.class);
    }

    // exchange = retrieve + additional info like http status code
    @Test
    public void badRequestExchangeTest() {
        Mono<Object> response = webClient
                .get()
                .uri("reactive-math/square/{number}/throw", 5)
                .exchangeToMono(this::exchange)
                .doOnNext(System.out::println);

        StepVerifier.create(response)
                .expectNextCount(1)
                .verifyComplete();
    }

    private Mono<Object> exchange(ClientResponse cr) {
        if (cr.statusCode().is4xxClientError())
            return cr.bodyToMono((InputFailedValidationResponse.class));
        return cr.bodyToMono(Response.class);
    }
}
