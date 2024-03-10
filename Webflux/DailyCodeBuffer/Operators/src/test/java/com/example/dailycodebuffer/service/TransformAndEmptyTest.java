package com.example.dailycodebuffer.service;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

class TransformAndEmptyTest {

    private final TransformAndEmpty service = new TransformAndEmpty();

    @Test
    void fruitsFluxTransform() {
        Flux<String> flux = service.fruitsFluxTransform(5);

        StepVerifier.create(flux)
                .expectNextCount(2) // ожидаем "Orange" & "Banana"
                .verifyComplete();
    }

    @Test
    void fruitsFluxDefaultIfEmpty() {
        Flux<String> flux = service.fruitsFluxDefaultIfEmpty(15);

        StepVerifier.create(flux)
                .expectNext("Default") // ожидаем "Default"
                .verifyComplete();
    }

    @Test
    void fruitsFluxSwitchIfEmpty() {
        Flux<String> flux = service.fruitsFluxSwitchIfEmpty(15);

        StepVerifier.create(flux)
                .expectNext("Apple") // ожидаем "Apple" из switch
                .expectNext("Melon") // ожидаем "Melon"
                .verifyComplete();
    }
}