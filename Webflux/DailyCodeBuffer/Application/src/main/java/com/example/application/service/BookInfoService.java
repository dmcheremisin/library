package com.example.application.service;

import com.example.application.domain.BookInfo;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public class BookInfoService {

    public Flux<BookInfo> getBookInfo() {
        List<BookInfo> books = List.of(
                new BookInfo(1, "Book One", "Author One", "1111111"),
                new BookInfo(2, "Book Two", "Author Two", "22222222"),
                new BookInfo(3, "Book Three", "Author Three", "33333333")
        );
        return Flux.fromIterable(books);
    }

    public Mono<BookInfo> getBookInfoById(long bookId) {
        BookInfo book = new BookInfo(bookId, "Book One", "Author One", "1111111");
        return Mono.just(book);
    }

}
