package com.example.dailycodebuffer.service;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

class FlatMapManyTest {

    private final FlatMapMany service = new FlatMapMany();

    @Test
    void fruitMonoFlatMapMany() {
        Flux<String> flux = service.fruitMonoFlatMapMany();

        StepVerifier.create(flux)
                .expectNextCount(5) // ожидаем 5 символов
                .verifyComplete();
    }
}