package com.example.dailycodebuffer.service;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

class ZipAndZipWithTest {

    private final ZipAndZipWith service = new ZipAndZipWith();

    @Test
    void fluxZip() {
        Flux<String> flux = service.fluxZip();

        StepVerifier.create(flux)
                .expectNext("MangoTomato") // ожидаем "MangoTomato", Mango - из первого, Tomato - из второго
                .expectNext("OrangePotato") // ожидаем "OrangePotato", Orange - из первого, Potato - из второго
                //"Banana" - пролетает мимо, для него нет пары
                .verifyComplete();
    }

    @Test
    void fluxZipTuple() {
        Flux<String> flux = service.fluxZipTuple();

        StepVerifier.create(flux)
                .expectNext("MangoTomato") // ожидаем "MangoTomato", Mango - из первого, Tomato - из второго
                .expectNext("OrangePotato") // ожидаем "OrangePotato", Orange - из первого, Potato - из второго
                //"Banana" - пролетает мимо, для него нет пары
                .verifyComplete();
    }

    @Test
    void fluxZipWith() {
        Flux<String> flux = service.fluxZipWith();

        StepVerifier.create(flux)
                .expectNext("MangoTomato") // ожидаем "MangoTomato", Mango - из первого, Tomato - из второго
                .expectNext("OrangePotato") // ожидаем "OrangePotato", Orange - из первого, Potato - из второго
                //"Banana" - пролетает мимо, для него нет пары
                .verifyComplete();
    }

    @Test
    void fluxZipTriple() {
        Flux<String> flux = service.fluxZipTriple();

        StepVerifier.create(flux)
                .expectNext("MangoTomatoLollipop")
                .expectNext("OrangePotatoSweetie")
                //"Banana" - пролетает мимо, для него нет пары
                .verifyComplete();
    }

    @Test
    void monoZipWith() {
        Mono<String> flux = service.monoZipWith();

        StepVerifier.create(flux)
                .expectNext("MangoTomato")
                .verifyComplete();
    }
}