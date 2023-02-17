package com.newtech.booksapi.controlers;

import com.newtech.booksapi.models.Book;
import com.newtech.booksapi.services.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@Controller
@RestController
@RequestMapping("api/Books")
@CrossOrigin("*")
public class BooksController {
    private final BookService bookService;

    public BooksController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> findAll(){
        return new ResponseEntity<>(bookService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> findById(@PathVariable Integer id ) throws IOException {

            Book book = bookService.findById(id);
            if(book == null){
                Map<String, Object> response = new HashMap<>();
                response.put("message","no se encontró el libro con el id: ".concat(id.toString()));
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
            URL url = new URL("https://fakerestapi.azurewebsites.net/swagger/ui/index");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            return new ResponseEntity<>(book, HttpStatus.OK);

    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> create(@RequestBody Book book){
        book.setId(bookService.findAll().size()+1);
        bookService.save(book);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> delete(@PathVariable Integer id ){
        Map<String, Object> response = new HashMap<>();
        if(bookService.deleteById(id)){
            response.put("message","eliminado con éxito libro con id: ".concat(id.toString()));
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        else {
            response.put("message","no se encontro el libro con el id: ".concat(id.toString()));
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> update(@RequestBody Book book, @PathVariable Integer id ){
        Map<String, Object> response = new HashMap<>();
        if(bookService.update(book,id) != null){
            response.put("message","actualizado con éxito libro con id: ".concat(id.toString()));
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        else {
            response.put("message","no se encontro el libro con el id: ".concat(id.toString()));
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

}
