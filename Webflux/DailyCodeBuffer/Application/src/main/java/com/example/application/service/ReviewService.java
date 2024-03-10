package com.example.application.service;

import com.example.application.domain.Review;
import reactor.core.publisher.Flux;

import java.util.List;

public class ReviewService {

    public Flux<Review> getReviews(long bookId) {
        List<Review> reviews = List.of(
                new Review(1, bookId, 9.1, "Good book"),
                new Review(1, bookId, 8.3, "Worth reading")
        );
        return Flux.fromIterable(reviews);
    }

}
