package com.example.dailycodebuffer.service;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

class OnErrorReturnContinueMapTest {

    private final OnErrorReturnContinueMap service = new OnErrorReturnContinueMap();

    @Test
    void fluxOnErrorReturn() {
        Flux<String> flux = service.fluxOnErrorReturn().log();

        StepVerifier.create(flux)
                .expectNext("Mango", "Orange", "Banana", "Apple")
                .verifyComplete();
    }

    @Test
    void fluxOnErrorContinue() {
        Flux<String> flux = service.fluxOnErrorContinue().log();

        StepVerifier.create(flux)
                .expectNext("Mango", "Banana")
                .verifyComplete();
        //e = java.lang.RuntimeException: No oranges
        //f = Orange
    }

    @Test
    void fluxOnErrorMap() {
        Flux<String> flux = service.fluxOnErrorMap().log();

        StepVerifier.create(flux)
                .expectNext("Mango")
                .expectError(IllegalStateException.class)
                .verify();
        //throwable = java.lang.RuntimeException: No oranges
        //java.lang.IllegalStateException: From error map
    }

    @Test
    void fluxDoOnError() {
        Flux<String> flux = service.fluxDoOnError().log();

        StepVerifier.create(flux)
                .expectNext("Mango")
                .expectError(RuntimeException.class)
                .verify();
        //throwable = java.lang.RuntimeException: No oranges
    }
}