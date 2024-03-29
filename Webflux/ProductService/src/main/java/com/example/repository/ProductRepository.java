package com.example.repository;

import com.example.dto.ProductDto;
import com.example.entity.Product;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface ProductRepository extends ReactiveMongoRepository<Product, String> {

    Flux<ProductDto> findAllByPriceGreaterThanEqualAndPriceIsLessThanEqual(Integer min, Integer max);

    Flux<Product> findAllByPriceBetween(Integer min, Integer max);
}
