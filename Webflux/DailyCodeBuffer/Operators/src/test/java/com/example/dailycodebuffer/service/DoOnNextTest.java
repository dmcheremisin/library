package com.example.dailycodebuffer.service;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

class DoOnNextTest {

    private final DoOnNext service = new DoOnNext();

    @Test
    void fluxDoOnNext() {
        Flux<String> flux = service.fluxDoOnNext();

        StepVerifier.create(flux)
                .expectNext("MANGO", "ORANGE", "BANANA")
                .verifyComplete();
    }
}