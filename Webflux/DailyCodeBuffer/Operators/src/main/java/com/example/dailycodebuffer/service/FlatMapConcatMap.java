package com.example.dailycodebuffer.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class FlatMapConcatMap {

    //flatMap оператор
    public Flux<String> fruitsFluxFlatMap() {
        return Flux.just("Mango", "Orange", "Banana")
                .flatMap(s -> Flux.just(s.split(""))) // сплитим строку на отдельные символы
                .log();
    }

    //flatMap с задержкой
    public Flux<String> fruitsFluxFlatMapAsync() {
        return Flux.just("Mango", "Orange", "Banana")
                .flatMap(s -> Flux.just(s.split(""))) // сплитим строку на отдельные символы
                .delayElements(Duration.ofMillis(new Random().nextInt(1000)))
                .log();
    }

    //flatMap оператор для Mono
    public Mono<List<String>> fruitMonoFlatMap() {
        return Mono.just("Mango")
                .flatMap(s -> Mono.just(List.of(s.split("")))) // переводим в Mono, содержащее лист символов
                .log();
    }

    //flatMap с задержкой
    public Flux<String> fruitsFluxConcatMap() {
        return Flux.just("Mango", "Orange", "Banana")
                .concatMap(s -> Flux.just(s.split(""))) // сохраняет порядок элементов
                .delayElements(Duration.ofMillis(new Random().nextInt(1000)))
                .log();
    }


    public static void main(String[] args) {
        FlatMapConcatMap flatMap = new FlatMapConcatMap();
        flatMap.fruitsFluxFlatMap().log().subscribe(System.out::println);
    }
}
