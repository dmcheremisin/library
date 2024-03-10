package com.example.dailycodebuffer.serivce;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

class FluxAndMonoServiceTest {

    private final FluxAndMonoService service = new FluxAndMonoService();

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
                .expectNext("Mango")
                .verifyComplete();
    }
}