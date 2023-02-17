package com.newtech.booksapi.services;

import com.newtech.booksapi.models.Book;
import com.newtech.booksapi.repository.FakeAPIClient;
import feign.Feign;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.okhttp.OkHttpClient;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BookService {

    private FakeAPIClient fakeAPIClient;

    private List<Book> books;
    public BookService() {
        this.fakeAPIClient =
                Feign.builder()
                .client(new OkHttpClient())
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .target(FakeAPIClient.class, "https://fakerestapi.azurewebsites.net/swagger/ui/index");
        books = new ArrayList<>();
    }

    public List<Book> findAll(){
        return books;
    }

    public Book findById(Integer id){
        return books.stream().filter(book -> Objects.equals(book.getId(), id)).findAny().orElse(null);
    }

    public void save(Book book){
        books.add(book);
        try {
            fakeAPIClient.registerBookISBN(book);
        }catch (Exception ignore){

        }
    }

    public Book update(Book book, Integer id){
        Book old = findById(id);
        if(old != null){
            old.setName(book.getName());
            old.setAuth(book.getAuth());
            old.setIsbn(book.getIsbn());
            old.setDate(book.getDate());
            old.setGender(book.getGender());
            try {
                fakeAPIClient.registerBookISBN(book);
            }catch (Exception ignore){

            }
        }
        return old;

    }

    public Boolean deleteById(Integer id){
        int ind = -1;
        Book book = findById(id);
        for (Book aux:books){
            ind++;
            if(Objects.equals(aux.getId(), id)){
                try{
                    fakeAPIClient.findByIsbn(aux.getIsbn());
                }catch (Exception ignore){

                }

                break;
            }
        }
        return ind > -1 && books.remove(book);
    }


}
