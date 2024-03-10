package com.dmch.math.controller;

import com.dmch.math.BaseTest;
import com.dmch.math.dto.Response;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class PostRequestTest extends BaseTest {

    @Test
    public void postTest() {
        Mono<Response> response = webClient
                .post()
                .uri("reactive-math/multiply")
                .bodyValue(buildRequestDto(3, 4))
                .retrieve()
                .bodyToMono(Response.class)
                .doOnNext(System.out::println);

        StepVerifier.create(response)
                .expectNext(new Response(12))
//                .expectNextCount(1)
                .verifyComplete();
    }

    @Test
    public void headersTest() {
        Mono<Response> response = webClient
                .post()
                .uri("reactive-math/multiply")
                .bodyValue(buildRequestDto(3, 4))
                .headers(headers -> headers.set("someKey", "someVal"))
                .retrieve()
                .bodyToMono(Response.class)
                .doOnNext(System.out::println);

        StepVerifier.create(response)
                .expectNext(new Response(12))
                //                .expectNextCount(1)
                .verifyComplete();
    }

}
