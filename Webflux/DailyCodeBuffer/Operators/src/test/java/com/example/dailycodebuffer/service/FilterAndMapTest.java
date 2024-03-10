package com.example.dailycodebuffer.service;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

class FilterAndMapTest {

    private final FilterAndMap service = new FilterAndMap();

    @Test
    void fruitsFluxMap() {
        Flux<String> flux = service.fruitsFluxMap();

        StepVerifier.create(flux)
                .expectNext("MANGO", "ORANGE", "BANANA")
                .verifyComplete();
    }

    @Test
    void fruitsFluxFilter() {
        Flux<String> flux = service.fruitsFluxFilter();

        StepVerifier.create(flux)
                .expectNext("Orange", "Banana")
                .verifyComplete();
    }

    @Test
    void fruitsFluxFilterMap() {
        Flux<String> flux = service.fruitsFluxFilterMap();

        StepVerifier.create(flux)
                .expectNext("ORANGE", "BANANA")
                .verifyComplete();
    }
}