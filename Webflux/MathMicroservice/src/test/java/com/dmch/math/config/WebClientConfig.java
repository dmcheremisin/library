package com.dmch.math.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.ExchangeFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .baseUrl("http://localhost:8080")
                .filter((clientRequest, exchangeFunction) -> sessionToken(clientRequest, exchangeFunction))
//                .defaultHeaders(h -> h.set("login", "pass"))
                .build();
    }

    private Mono<ClientResponse> sessionToken(ClientRequest request, ExchangeFunction ex) {
        System.out.println("generating session token");
        ClientRequest clientRequest = ClientRequest.from(request).headers(h -> h.setBearerAuth("some-jwt")).build();
        return ex.exchange(clientRequest);
    }
}
