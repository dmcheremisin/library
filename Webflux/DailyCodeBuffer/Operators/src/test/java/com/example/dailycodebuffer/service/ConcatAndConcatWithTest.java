package com.example.dailycodebuffer.service;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

class ConcatAndConcatWithTest {

    private final ConcatAndConcatWith service = new ConcatAndConcatWith();

    @Test
    void fluxConcat() {
        Flux<String> flux = service.fluxConcat().log();

        StepVerifier.create(flux)
                .expectNext("Mango", "Orange", "Banana", "Tomato", "Potato")
                .verifyComplete();
    }

    @Test
    void fluxConcatWith() {
        Flux<String> flux = service.fluxConcatWith().log();

        StepVerifier.create(flux)
                .expectNext("Mango", "Orange", "Banana", "Tomato", "Potato")
                .verifyComplete();
    }

    @Test
    void monoConcatWith() {
        Flux<String> flux = service.monoConcatWith().log();

        StepVerifier.create(flux)
                .expectNext("Mango", "Tomato")
                .verifyComplete();
    }
}