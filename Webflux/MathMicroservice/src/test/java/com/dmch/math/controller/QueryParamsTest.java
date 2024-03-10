package com.dmch.math.controller;

import com.dmch.math.BaseTest;
import org.junit.jupiter.api.Test;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.net.URI;
import java.util.Map;

public class QueryParamsTest extends BaseTest {

    public static final String URL = "http://localhost:8080/jobs/search?count={count}&page={page}";

    @Test
    public void queryParamsTest() {
        URI uri = UriComponentsBuilder.fromUriString(URL)
                .build(10, 20);
        Flux<Integer> integerFlux = webClient.get()
                .uri(uri)
                .retrieve()
                .bodyToFlux(Integer.class)
                .doOnNext(System.out::println);

        StepVerifier.create(integerFlux)
                .expectNextCount(2)
                .verifyComplete();
    }

    @Test
    public void queryParamsBuilderTest() {
        Flux<Integer> integerFlux = webClient.get()
                .uri(b -> b.path("jobs/search").query("count={count}&page={page}").build(10, 20))
                .retrieve()
                .bodyToFlux(Integer.class)
                .doOnNext(System.out::println);

        StepVerifier.create(integerFlux)
                .expectNextCount(2)
                .verifyComplete();
    }

    @Test
    public void queryParamsMapTest() {
        Map<String, Integer> map = Map.of("count", 10, "page", 20);
        Flux<Integer> integerFlux = webClient.get()
                .uri(b -> b.path("jobs/search").query("count={count}&page={page}").build(map))
                .retrieve()
                .bodyToFlux(Integer.class)
                .doOnNext(System.out::println);

        StepVerifier.create(integerFlux)
                .expectNextCount(2)
                .verifyComplete();
    }

    @Test
    public void queryParams2Test() {
        Flux<Integer> integerFlux = webClient.get()
                .uri(b -> b.path("jobs/search").queryParam("count", 10).queryParam("page", 20).build())
                .retrieve()
                .bodyToFlux(Integer.class)
                .doOnNext(System.out::println);

        StepVerifier.create(integerFlux)
                .expectNextCount(2)
                .verifyComplete();
    }
}
