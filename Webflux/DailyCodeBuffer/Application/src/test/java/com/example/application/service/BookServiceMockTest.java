package com.example.application.service;

import com.example.application.exception.BookException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class BookServiceMockTest {

    @Mock
    private BookInfoService bookInfoService;

    @Mock
    private ReviewService reviewService;

    @InjectMocks
    private BookService bookService;

    @Test
    void getBooksMock() {
        Mockito.when(bookInfoService.getBookInfo())
                .thenCallRealMethod();
        Mockito.when(reviewService.getReviews(Mockito.anyLong()))
                .thenCallRealMethod();

        var books = bookService.getBooksOnError();

        StepVerifier.create(books)
                .expectNextCount(3)
                .verifyComplete();
    }

    @Test
    void getBooksMockOnError() {
        Mockito.when(bookInfoService.getBookInfo())
                .thenCallRealMethod();
        Mockito.when(reviewService.getReviews(Mockito.anyLong()))
                .thenThrow(new IllegalStateException("Exception during fetching review"));

        var books = bookService.getBooksOnError();

        StepVerifier.create(books)
                .expectError(BookException.class)
                .verify();
    }

    @Test
    void getBooksMockErrorRetry() {
        Mockito.when(bookInfoService.getBookInfo())
                .thenCallRealMethod();
        Mockito.when(reviewService.getReviews(Mockito.anyLong()))
                .thenThrow(new IllegalStateException("Exception during fetching review"));

        var books = bookService.getBooksRetry();

        StepVerifier.create(books)
                .expectError(BookException.class)
                .verify();
    }

    @Test
    void getBooksMockErrorRetryWhen() {
        Mockito.when(bookInfoService.getBookInfo())
                .thenCallRealMethod();
        Mockito.when(reviewService.getReviews(Mockito.anyLong()))
                .thenThrow(new IllegalStateException("Exception during fetching review"));

        var books = bookService.getBooksRetryWhen();

        StepVerifier.create(books)
                .expectError(BookException.class)
                .verify();
    }
}