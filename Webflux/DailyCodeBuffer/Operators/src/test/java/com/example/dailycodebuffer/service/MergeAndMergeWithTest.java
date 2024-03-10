package com.example.dailycodebuffer.service;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

class MergeAndMergeWithTest {

    private final MergeAndMergeWith service = new MergeAndMergeWith();

    @Test
    void fluxMerge() {
        Flux<String> flux = service.fluxMerge().log();

        StepVerifier.create(flux)
                .expectNext("Mango", "Tomato", "Orange", "Banana", "Potato")
                .verifyComplete();
    }

    @Test
    void fluxMergeSequential() {
        Flux<String> flux = service.fluxMergeSequential().log();

        StepVerifier.create(flux)
                .expectNext("Mango", "Orange", "Banana", "Tomato", "Potato")
                .verifyComplete();
    }

    @Test
    void fluxMergeWith() {
        Flux<String> flux = service.fluxMergeWith().log();

        StepVerifier.create(flux)
                .expectNext("Mango", "Tomato", "Orange", "Banana", "Potato")
                .verifyComplete();
    }

}