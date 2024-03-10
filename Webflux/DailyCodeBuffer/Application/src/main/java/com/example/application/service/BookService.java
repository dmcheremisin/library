package com.example.application.service;

import com.example.application.domain.Book;
import com.example.application.domain.BookInfo;
import com.example.application.domain.Review;
import com.example.application.exception.BookException;
import lombok.extern.slf4j.Slf4j;
import reactor.core.Exceptions;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.util.List;

@Slf4j
public class BookService {

    private final BookInfoService bookInfoService;
    private final ReviewService reviewService;

    public BookService(BookInfoService bookInfoService, ReviewService reviewService) {
        this.bookInfoService = bookInfoService;
        this.reviewService = reviewService;
    }

    public Flux<Book> getBooks() {
        Flux<BookInfo> allBookInfo = bookInfoService.getBookInfo();
        return allBookInfo
                .flatMap(bookInfo -> {
                    Mono<List<Review>> reviewsMono = reviewService.getReviews(bookInfo.getBookId()).collectList();
                    return reviewsMono
                            .map(reviews -> new Book(bookInfo, reviews));
                })
                .log();
    }

    public Mono<Book> getBookById(long id) {
        Mono<BookInfo> bookInfoById = bookInfoService.getBookInfoById(id);
        Flux<Review> reviews = reviewService.getReviews(id);
        return bookInfoById.zipWith(reviews.collectList(), Book::new);
    }

    public Flux<Book> getBooksOnError() {
        Flux<BookInfo> allBookInfo = bookInfoService.getBookInfo();
        return allBookInfo
                .flatMap(bookInfo -> {
                    Mono<List<Review>> reviewsMono = reviewService.getReviews(bookInfo.getBookId()).collectList();
                    return reviewsMono
                            .map(reviews -> new Book(bookInfo, reviews));
                })
                .onErrorMap(throwable -> {
                    log.error("Exception is : " + throwable);
                    return new BookException("Exception occurred while fetching book");
                })
                .log();
    }

    public Flux<Book> getBooksRetry() {
        Flux<BookInfo> allBookInfo = bookInfoService.getBookInfo();
        return allBookInfo
                .flatMap(bookInfo -> {
                    Mono<List<Review>> reviewsMono = reviewService.getReviews(bookInfo.getBookId()).collectList();
                    return reviewsMono
                            .map(reviews -> new Book(bookInfo, reviews));
                })
                .onErrorMap(throwable -> {
                    log.error("Exception is : " + throwable);
                    return new BookException("Exception occurred while fetching book");
                })
                .retry(3)
                .log();
    }

    public Flux<Book> getBooksRetryWhen() {
        var retrySpecs = Retry.backoff(3, Duration.ofMillis(1000))
                .filter(throwable -> throwable instanceof BookException)
                .onRetryExhaustedThrow(((retryBackoffSpec, retrySignal) -> Exceptions.propagate(retrySignal.failure())));

        Flux<BookInfo> allBookInfo = bookInfoService.getBookInfo();
        return allBookInfo
                .flatMap(bookInfo -> {
                    Mono<List<Review>> reviewsMono = reviewService.getReviews(bookInfo.getBookId()).collectList();
                    return reviewsMono
                            .map(reviews -> new Book(bookInfo, reviews));
                })
                .onErrorMap(throwable -> {
                    log.error("Exception is : " + throwable);
                    return new BookException("Exception occurred while fetching book");
                })
                .retryWhen(retrySpecs)
                .log();
    }
}
