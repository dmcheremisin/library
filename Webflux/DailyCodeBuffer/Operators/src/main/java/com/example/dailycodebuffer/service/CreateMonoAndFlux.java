package com.example.dailycodebuffer.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class CreateMonoAndFlux {

    //как создать Mono
    public Mono<String> fruitMono() {
        return Mono.just("MangoMono");
    }

    //2 варианта как создать Flux
    public Flux<String> fruitsFlux() {
        //return Flux.fromIterable((List.of("Mango", "Orange", "Banana")));
        return Flux.just("Mango", "Orange", "Banana");
    }

    public static void main(String[] args) {
        CreateMonoAndFlux service = new CreateMonoAndFlux();

        service.fruitsFlux().log().subscribe(System.out::println);
        service.fruitMono().log().subscribe(System.out::println);
    }

}
