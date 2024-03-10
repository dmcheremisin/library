package com.example.dailycodebuffer.serivce;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class FluxAndMonoService {

    public Flux<String> fruitsFlux() {
        return Flux.just("Mango", "Orange", "Banana");
    }

    public Mono<String> fruitMono() {
        return Mono.just("Mango");
    }

    public static void main(String[] args) {
        FluxAndMonoService service = new FluxAndMonoService();

        service.fruitsFlux().log().subscribe(System.out::println);
        service.fruitMono().log().subscribe(System.out::println);
    }

}
