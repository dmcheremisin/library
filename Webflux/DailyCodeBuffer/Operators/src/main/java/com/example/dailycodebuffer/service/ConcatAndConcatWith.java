package com.example.dailycodebuffer.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class ConcatAndConcatWith {

    //concat оператор
    public Flux<String> fluxConcat() {
        Flux<String> fruits = Flux.just("Mango", "Orange", "Banana");
        Flux<String> veggies = Flux.just("Tomato", "Potato");

        return Flux.concat(fruits, veggies);
    }

    //concatWith оператор Flux
    public Flux<String> fluxConcatWith() {
        Flux<String> fruits = Flux.just("Mango", "Orange", "Banana");
        Flux<String> veggies = Flux.just("Tomato", "Potato");

        return fruits.concatWith(veggies);
    }

    //concatWith оператор
    public Flux<String> monoConcatWith() {
        Mono<String> fruits = Mono.just("Mango");
        Mono<String> veggies = Mono.just("Tomato");

        return fruits.concatWith(veggies);
    }

}
