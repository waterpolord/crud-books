package com.newtech.booksapi.services;

import com.newtech.booksapi.models.Book;
import com.newtech.booksapi.repository.FakeAPIClient;
import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookServiceTest {
    @Mock
    private BookService bookService;
    private List<Book> books = new ArrayList<>();
    private Book book, book2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        book = new Book();
        book.setId(1);
        book.setName("48 Laws of power");
        book.setAuth("Robert Greene");
        book.setDate(LocalDate.now());
        book.setGender("Self Improve");
        book.setIsbn("4879544878265");
        books.add(book);

        book2 = new Book();
        book2.setId(1);
        book2.setName("48 Laws of power");
        book2.setAuth("Robert Greene");
        book2.setDate(LocalDate.now());
        book2.setGender("Self Improve");
        book2.setIsbn("4879544878265");

    }

    @Test
    void findAll() {
        when(bookService.findAll()).thenReturn(books);
        assertNotNull(bookService.findAll());
    }

    @Test
    void findById() {
        when(bookService.findById(1)).thenReturn(book);
    }

    @Test
    void update() {
        when(bookService.update(book,1)).thenReturn(book);

    }

    @Test
    void deleteById() {
        when(bookService.deleteById(1)).thenReturn(true);
    }
}