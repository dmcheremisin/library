package com.dmch.math.controller;

import com.dmch.math.dto.MultiplyRequestDto;
import com.dmch.math.dto.Response;
import com.dmch.math.service.MathService;
import com.dmch.math.service.ReactiveMathService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping("/reactive-math")
@RequiredArgsConstructor
public class ReactiveMathController {

    private final ReactiveMathService reactiveMathService;

    @GetMapping("/square/{input}")
    public Mono<Response> findSquare(@PathVariable int input) {
        return reactiveMathService.findSquare(input);
    }

    @GetMapping("/table/{input}")
    public Flux<Response> multiplicationTable(@PathVariable int input) {
        return reactiveMathService.multiplicationTable(input);
    }

    @GetMapping(value = "/table/{input}/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Response> streamTable(@PathVariable int input) {
        return reactiveMathService.multiplicationTable(input);
    }

    @PostMapping("/multiply")
    public Mono<Response> multiply(@RequestBody Mono<MultiplyRequestDto> request,
                                   @RequestHeader Map<String, String> headers) {
        System.out.println(headers);
        return reactiveMathService.multiply(request);
    }
}
