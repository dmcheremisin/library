package com.example.dailycodebuffer.service;

import reactor.core.publisher.Flux;

import java.util.List;

public class FilterAndMap {

    //Map оператор
    public Flux<String> fruitsFluxMap() {
        return Flux.fromIterable((List.of("Mango", "Orange", "Banana")))
                .map(String::toUpperCase);
        //.log();
    }

    //Filter оператор
    public Flux<String> fruitsFluxFilter() {
        return Flux.fromIterable((List.of("Mango", "Orange", "Banana")))
                .filter(s -> s.length() > 5);
    }

    //Filter&Map оператор
    public Flux<String> fruitsFluxFilterMap() {
        return Flux.fromIterable((List.of("Mango", "Orange", "Banana")))
                .filter(s -> s.length() > 5)
                .map(String::toUpperCase);
    }

}
