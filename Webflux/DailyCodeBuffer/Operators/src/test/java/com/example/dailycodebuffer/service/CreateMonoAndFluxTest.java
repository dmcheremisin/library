package com.example.dailycodebuffer.service;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

class CreateMonoAndFluxTest {

    private final CreateMonoAndFlux service = new CreateMonoAndFlux();

    @Test
    void fruitsFlux() {
        Flux<String> flux = service.fruitsFlux();

        StepVerifier.create(flux)
                .expectNext("Mango", "Orange", "Banana")
                .verifyComplete();
    }

    @Test
    void fruitMono() {
        var fruitMono = service.fruitMono();
        StepVerifier.create(fruitMono)
                .expectNext("MangoMono")
                .verifyComplete();
    }
}