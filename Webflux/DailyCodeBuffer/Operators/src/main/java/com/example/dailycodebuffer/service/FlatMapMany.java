package com.example.dailycodebuffer.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class FlatMapMany {

    //flatMapMany оператор для Mono
    public Flux<String> fruitMonoFlatMapMany() {
        return Mono.just("Mango")
                .flatMapMany(s -> Flux.just(s.split(""))) // переводим в Flux, содержащий лист символов
                .log();
    }

}
