package com.example.dailycodebuffer.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class ZipAndZipWith {

    //zip оператор
    public Flux<String> fluxZip() {
        Flux<String> fruits = Flux.just("Mango", "Orange", "Banana");
        Flux<String> veggies = Flux.just("Tomato", "Potato");

        return Flux.zip(fruits, veggies, (first, second) -> first + second)
                .log();
    }

    //zip оператор
    public Flux<String> fluxZipTuple() {
        Flux<String> fruits = Flux.just("Mango", "Orange", "Banana");
        Flux<String> veggies = Flux.just("Tomato", "Potato");

        return Flux.zip(fruits, veggies).map(tuple -> tuple.getT1() + tuple.getT2())
                .log();
    }

    //zipWith оператор
    public Flux<String> fluxZipWith() {
        Flux<String> fruits = Flux.just("Mango", "Orange", "Banana");
        Flux<String> veggies = Flux.just("Tomato", "Potato");

        return fruits.zipWith(veggies, (first, second) -> first + second)
                .log();
    }

    //zip оператор с тремя flux
    public Flux<String> fluxZipTriple() {
        Flux<String> fruits = Flux.just("Mango", "Orange", "Banana");
        Flux<String> veggies = Flux.just("Tomato", "Potato");
        Flux<String> candies = Flux.just("Lollipop", "Sweetie");

        return fruits.zip(fruits, veggies, candies)
                .map(triple -> triple.getT1() + triple.getT2() + triple.getT3());
    }

    //zipWith оператор с Mono
    public Mono<String> monoZipWith() {
        Mono<String> fruits = Mono.just("Mango");
        Mono<String> veggies = Mono.just("Tomato");

        return fruits.zipWith(veggies, (first, second) -> first + second)
                .log();
    }

}
