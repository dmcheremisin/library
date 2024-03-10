package com.dmch.math.config;

import com.dmch.math.dto.MultiplyRequestDto;
import com.dmch.math.dto.Response;
import com.dmch.math.exception.InputValidationException;
import com.dmch.math.exception.OperationValidationException;
import com.dmch.math.service.ReactiveMathService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RequestHandler {

    private final ReactiveMathService reactiveMathService;

    public Mono<ServerResponse> operation(ServerRequest serverRequest) {
        int first = Integer.parseInt(serverRequest.pathVariable("first"));
        int second = Integer.parseInt(serverRequest.pathVariable("second"));
        String OP = serverRequest.headers().header("OP").get(0);
        Response response;
        switch (OP) {
            case "+" -> response = new Response(first + second);
            case "-" -> response = new Response(first - second);
            case "*" -> response = new Response(first * second);
            case "/" -> response = new Response((double) first / second);
            default -> {
                return Mono.error(new OperationValidationException(first, second));
            }
        }
        Mono<Response> mono = Mono.fromSupplier(() -> response);
        return ServerResponse.ok().body(mono, Response.class);
    }

    public Mono<ServerResponse> squareHandler(ServerRequest serverRequest) {
        int input = Integer.parseInt(serverRequest.pathVariable("input"));
        Mono<Response> mono = reactiveMathService.findSquare(input);
        return ServerResponse.ok().body(mono, Response.class);
    }

    public Mono<ServerResponse> tableHandler(ServerRequest serverRequest) {
        int input = Integer.parseInt(serverRequest.pathVariable("input"));
        Flux<Response> flux = reactiveMathService.multiplicationTable(input);
        return ServerResponse.ok().body(flux, Response.class);
    }

    public Mono<ServerResponse> tableStreamHandler(ServerRequest serverRequest) {
        int input = Integer.parseInt(serverRequest.pathVariable("input"));
        Flux<Response> flux = reactiveMathService.multiplicationTable(input);
        return ServerResponse.ok()
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .body(flux, Response.class);
    }

    public Mono<ServerResponse> squareWithValidation(ServerRequest serverRequest) {
        int input = Integer.parseInt(serverRequest.pathVariable("input"));
        if (input < 10 || input > 20) {
            return Mono.error(new InputValidationException(input));
        }
        Mono<Response> mono = reactiveMathService.findSquare(input);
        return ServerResponse.ok().body(mono, Response.class);
    }

    public Mono<ServerResponse> multiplyHandler(ServerRequest serverRequest) {
        List<String> myKey = serverRequest.headers().header("MyKey");
        System.out.println(myKey);
        Mono<MultiplyRequestDto> requestDtoMono = serverRequest.bodyToMono(MultiplyRequestDto.class);
        Mono<Response> responseMono = reactiveMathService.multiply(requestDtoMono);
        return ServerResponse.ok().body(responseMono, Response.class);
    }
}
