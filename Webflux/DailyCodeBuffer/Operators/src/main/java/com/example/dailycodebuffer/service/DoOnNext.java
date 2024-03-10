package com.example.dailycodebuffer.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class DoOnNext {

    //doOnNext оператор
    public Flux<String> fluxDoOnNext() {
        return Flux.just("Mango", "Orange", "Banana")
                .doOnNext(System.out::println)
                .map(String::toUpperCase)
                .log();
    }

    //doOnSubscribe оператор
    public Flux<String> fluxDoOnSubscribe() {
        return Flux.just("Mango", "Orange", "Banana")
                .doOnSubscribe(subscription -> System.out.printf("s = " + subscription))
                .log();
    }

    //doOnSubscribe оператор
    public Flux<String> fluxDoOnComplete() {
        return Flux.just("Mango", "Orange", "Banana")
                .doOnComplete(() -> System.out.println("Finished"))
                .log();
    }


    public static void main(String[] args) {
        DoOnNext flatMap = new DoOnNext();
        flatMap.fluxDoOnSubscribe().log().subscribe(System.out::println);
        //s = reactor.core.publisher.FluxArray$ArraySubscription@148080bb17:40:21.851 [main]
        // INFO reactor.Flux.PeekFuseable.1 - | onSubscribe([Fuseable] FluxPeekFuseable.PeekFuseableSubscriber)

        flatMap.fluxDoOnComplete().log().subscribe(System.out::println);
        //Finished
    }
}
