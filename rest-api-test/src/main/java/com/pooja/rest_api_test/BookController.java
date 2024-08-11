package com.pooja.rest_api_test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private  BookService bookService;

    @Autowired
    public BookController(BookService bookService){
        this.bookService=bookService;
    }
    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks(){
        List<Book> books=bookService.getAllBooks();
        return ResponseEntity.ok(books);
    }

    @GetMapping("{id}")
    public ResponseEntity<Book> getBookById(@PathVariable int id){
        return ResponseEntity.ok(bookService.getBookById(id));
    }

    @PostMapping
    public ResponseEntity<Book> saveBook(@RequestBody Book book){
        return ResponseEntity.ok(bookService.saveBook(book));
    }

    @PutMapping
    public ResponseEntity<Book> updateBook(@RequestBody Book book){
        return ResponseEntity.ok(bookService.updateBook(book));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable int id){
        return ResponseEntity.ok(bookService.deleteBook(id));
    }

}
