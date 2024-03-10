package com.example.controller;

import com.example.dto.ProductDto;
import com.example.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public Flux<ProductDto> getAll() {
        return productService.getAll();
    }

    @GetMapping("/price-range")
    public Flux<ProductDto> getAllByPriceRange(@RequestParam Integer min, @RequestParam Integer max) {
        return productService.getAllByPriceRange(min, max);
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<ProductDto>> getById(@PathVariable String id) {
        return productService.getProductById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Mono<ProductDto> insertProduct(@RequestBody Mono<ProductDto> productDtoMono) {
        return productService.insertProduct(productDtoMono);
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<ProductDto>> getById(@PathVariable String id, @RequestBody Mono<ProductDto> productDtoMono) {
        return productService.updateProduct(id, productDtoMono)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteProduct(@PathVariable String id) {
        return productService.deleteProduct(id);
    }

}
