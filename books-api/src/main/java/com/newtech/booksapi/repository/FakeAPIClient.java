package com.newtech.booksapi.repository;

import com.newtech.booksapi.models.Book;
import feign.Headers;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient("books")
public interface FakeAPIClient {


    @RequestLine("POST /book")
    void registerBookISBN(@RequestBody Book book);

    @RequestLine("GET /{isbn}")
    Book findByIsbn(@PathVariable String isbn);

}
