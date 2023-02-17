package com.newtech.booksapi.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Getter
@Setter
@Component
public class Book {

    private Integer id;
    private String name;
    private String auth;
    private LocalDate date;
    private String gender;
    private String isbn;

}
