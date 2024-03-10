package com.dmch.math.controller;

import com.dmch.math.BaseTest;
import com.dmch.math.dto.Response;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class GetMultiResponseTest extends BaseTest {

    @Test
    public void fluxTest() {
        Flux<Response> response = webClient
                .get()
                .uri("reactive-math/table/{number}", 5)
                .retrieve()
                .bodyToFlux(Response.class)
                .doOnNext(System.out::println);

        StepVerifier.create(response)
                .expectNextCount(10)
                .verifyComplete();
    }

    @Test
    public void fluxStreamTest() {
        Flux<Response> response = webClient
                .get()
                .uri("reactive-math/table/{number}/stream", 5)
                .retrieve()
                .bodyToFlux(Response.class)
                .doOnNext(System.out::println);

        StepVerifier.create(response)
                .expectNextCount(10)
                .verifyComplete();
    }
}
