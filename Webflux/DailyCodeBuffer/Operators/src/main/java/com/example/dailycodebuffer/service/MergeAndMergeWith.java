package com.example.dailycodebuffer.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class MergeAndMergeWith {

    //merge оператор
    public Flux<String> fluxMerge() {
        Flux<String> fruits = Flux.just("Mango", "Orange", "Banana")
                .delayElements(Duration.ofMillis(500));
        Flux<String> veggies = Flux.just("Tomato", "Potato")
                .delayElements(Duration.ofMillis(800));

        return Flux.merge(fruits, veggies);
    }

    //mergeWithSequential оператор
    public Flux<String> fluxMergeSequential() {
        Flux<String> fruits = Flux.just("Mango", "Orange", "Banana")
                .delayElements(Duration.ofMillis(500));
        Flux<String> veggies = Flux.just("Tomato", "Potato")
                .delayElements(Duration.ofMillis(800));

        return Flux.mergeSequential(fruits, veggies);
    }

    //mergeWith оператор
    public Flux<String> fluxMergeWith() {
        Flux<String> fruits = Flux.just("Mango", "Orange", "Banana")
                .delayElements(Duration.ofMillis(500));
        Flux<String> veggies = Flux.just("Tomato", "Potato")
                .delayElements(Duration.ofMillis(800));

        return fruits.mergeWith(veggies);
    }

}
