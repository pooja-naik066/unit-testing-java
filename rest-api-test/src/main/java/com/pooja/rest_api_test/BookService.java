package com.pooja.rest_api_test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private BookRepository bookRepository;

    @Autowired
    public  BookService(BookRepository bookRepository){
        this.bookRepository=bookRepository;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(int id){
        Optional<Book> book=bookRepository.findById(id);
        if(book.isEmpty()){
            throw new RuntimeException("Book Not found");
        }
        return book.get();
    }

    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    public Book updateBook(Book book)  {
        if(book == null){
            throw new RuntimeException("Book cannot be null");
        }
        Optional<Book> savedBook=bookRepository.findById(book.getId());
        if (savedBook.isEmpty())
            throw new RuntimeException("Book not found");
        savedBook.get().setTitle(book.getTitle());
        savedBook.get().setRating(book.getRating());
        return bookRepository.save(savedBook.get());
    }

    public String deleteBook(int id) {
        Optional<Book> book=bookRepository.findById(id);
        if (book.isEmpty())
            throw new RuntimeException("Book not found");
        bookRepository.deleteById(id);
        return "Book Deleted";
    }
}
