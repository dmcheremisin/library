package com.example.dailycodebuffer.service;

import reactor.core.publisher.Flux;

public class OnErrorReturnContinueMap {

    public static void main(String[] args) {
        OnErrorReturnContinueMap flatMap = new OnErrorReturnContinueMap();
        flatMap.fluxOnErrorReturn().log().subscribe(System.out::println);
        //"Mango", "Orange", "Banana", "Apple"
    }

    //OnErrorReturn оператор
    public Flux<String> fluxOnErrorReturn() {
        return Flux.just("Mango", "Orange", "Banana")
                .concatWith(Flux.error(new RuntimeException("Exception occurred")))
                .onErrorReturn("Apple");
    }

    //OnErrorContinue оператор
    public Flux<String> fluxOnErrorContinue() {
        return Flux.just("Mango", "Orange", "Banana")
                .map(s -> {
                    if (s.equals("Orange"))
                        throw new RuntimeException("No oranges");
                    else
                        return s;
                })
                .onErrorContinue((e, f) -> {
                    System.out.println("e = " + e);
                    System.out.println("f = " + f);
                });
    }

    //OnErrorMap оператор
    public Flux<String> fluxOnErrorMap() {
        return Flux.just("Mango", "Orange", "Banana")
                .map(s -> {
                    if (s.equals("Orange"))
                        throw new RuntimeException("No oranges");
                    else
                        return s;
                })
                .onErrorMap(throwable -> {
                    System.out.println("throwable = " + throwable);
                    return new IllegalStateException("From error map");
                });
    }

    //doOnError оператор
    public Flux<String> fluxDoOnError() {
        return Flux.just("Mango", "Orange", "Banana")
                .map(s -> {
                    if (s.equals("Orange"))
                        throw new RuntimeException("No oranges");
                    else
                        return s;
                })
                .doOnError(throwable -> {
                    System.out.println("throwable = " + throwable);
                });
    }
}
