package com.example.application.service;

import com.example.application.domain.Book;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BookServiceTest {

    private BookInfoService bookInfoService = new BookInfoService();
    private ReviewService reviewService = new ReviewService();

    private BookService bookService = new BookService(bookInfoService, reviewService);

    @Test
    void getBooks() {
        Flux<Book> books = bookService.getBooks();

        StepVerifier.create(books)
                .assertNext(book -> {
                    assertEquals("Book One", book.getBookInfo().getTitle());
                    assertEquals(2, book.getReviews().size());
                })
                .assertNext(book -> {
                    assertEquals("Book Two", book.getBookInfo().getTitle());
                    assertEquals(2, book.getReviews().size());
                })
                .assertNext(book -> {
                    assertEquals("Book Three", book.getBookInfo().getTitle());
                    assertEquals(2, book.getReviews().size());
                })
                .verifyComplete();
    }

    @Test
    void getBookById() {
        Mono<Book> bookById = bookService.getBookById(123);
        StepVerifier.create(bookById)
                .assertNext(book -> {
                    assertEquals(123, book.getBookInfo().getBookId());
                    assertEquals("Book One", book.getBookInfo().getTitle());
                    assertEquals("Author One", book.getBookInfo().getAuthor());
                    assertEquals("1111111", book.getBookInfo().getISBN());
                })
                .verifyComplete();
    }
}