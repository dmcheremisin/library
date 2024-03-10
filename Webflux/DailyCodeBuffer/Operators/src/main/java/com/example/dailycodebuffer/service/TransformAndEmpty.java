package com.example.dailycodebuffer.service;

import reactor.core.publisher.Flux;

import java.util.List;
import java.util.function.Function;

public class TransformAndEmpty {

    //transform оператор
    public Flux<String> fruitsFluxTransform(int number) {
        Function<Flux<String>, Flux<String>> filterData = data -> data.filter(s -> s.length() > number);

        return Flux.just("Mango", "Orange", "Banana")
                //.filter(s -> s.length() > number);
                .transform(filterData)
                .log();
    }

    //defaultIfEmpty оператор
    public Flux<String> fruitsFluxDefaultIfEmpty(int number) {
        Function<Flux<String>, Flux<String>> filterData = data -> data.filter(s -> s.length() > number);

        return Flux.just("Mango", "Orange", "Banana")
                .transform(filterData)
                .defaultIfEmpty("Default")
                .log();
    }

    //switchIfEmpty оператор
    public Flux<String> fruitsFluxSwitchIfEmpty(int number) {
        Function<Flux<String>, Flux<String>> filterData = data -> data.filter(s -> s.length() > number);

        return Flux.just("Mango", "Orange", "Banana")
                .transform(filterData)
                .switchIfEmpty(Flux.just("Apple", "Melon"))
                .log();
    }
}
