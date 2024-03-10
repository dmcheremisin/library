package com.example.dailycodebuffer.service;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;

class FlatMapConcatMapTest {

    private final FlatMapConcatMap service = new FlatMapConcatMap();

    @Test
    void fruitsFluxFlatMap() {
        Flux<String> flux = service.fruitsFluxFlatMap();

        StepVerifier.create(flux)
                .expectNextCount(17) // ожидаем 17 символов
                .verifyComplete();
    }

    @Test
    void fruitsFluxFlatMapAsync() {
        Flux<String> flux = service.fruitsFluxFlatMapAsync();

        StepVerifier.create(flux)
                .expectNextCount(17) // ожидаем 17 символов
                .verifyComplete();
    }

    @Test
    void fruitMonoFlatMap() {
        Mono<List<String>> mono = service.fruitMonoFlatMap();

        StepVerifier.create(mono)
                //.expectNextCount(1) // ожидаем 1 лист символов от Mango
                .expectNext(List.of("M", "a", "n", "g", "o"))
                .verifyComplete();
    }


    @Test
    void fruitsFluxConcatMapAsync() {
        Flux<String> flux = service.fruitsFluxConcatMap();

        StepVerifier.create(flux)
                .expectNextCount(17) // ожидаем 17 символов
                .verifyComplete();
    }
}