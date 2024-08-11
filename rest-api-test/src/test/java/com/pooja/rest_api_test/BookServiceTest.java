package com.pooja.rest_api_test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class BookServiceTest {
    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);  // Initializes the mocks and injects them into the service
    }


    @Test
    void getAllBooks() {
        Book book1=new Book(1,"Pretty Little Things",5);
        Book book2=new Book(2,"The Moral Compass",3);
        Book book3=new Book(3,"Atomic Habits",4);

        List<Book> bookList= Arrays.asList(book1,book2,book3);
        Mockito.when(bookRepository.findAll()).thenReturn(bookList);
        List<Book> foundBooks=bookService.getAllBooks();

        assertEquals(3,foundBooks.size());
        assertEquals("The Moral Compass",foundBooks.get(1).getTitle());
        assertEquals(4,foundBooks.get(2).getRating());
    }


    @Test
    void getBookById() {
        Book book1=new Book(1,"Pretty Little Things",5);
        Mockito.when(bookRepository.findById(book1.getId())).thenReturn(Optional.of(book1));
        Book foundBook=bookService.getBookById(book1.getId());
        assertEquals(5,foundBook.getRating());
    }


    @Test
    void saveBook() {
        Book book1=new Book(1,"Pretty Little Things",5);
        Mockito.when(bookRepository.save(book1)).thenReturn(book1);
        Book savedBook=bookService.saveBook(book1);
        assertNotNull(savedBook);
    }


    @Test
    void updateBook() {
        Book book1=new Book(1,"Pretty Little Things",5);
        Book book2=new Book(1,"Pretty Little Things",3);
        Mockito.when(bookRepository.findById(book2.getId())).thenReturn(Optional.of(book1));
        Mockito.when(bookRepository.save(book1)).thenReturn(book1);
        Book updatedBook=bookService.updateBook(book2);
        assertEquals(3,updatedBook.getRating());
        assertEquals(book2,updatedBook);
    }


    @Test
    void deleteBook() {
        Book book1=new Book(1,"Pretty Little Things",5);
        Mockito.when(bookRepository.findById(book1.getId())).thenReturn(Optional.of(book1));
        String result=bookService.deleteBook(book1.getId());
        assertEquals("Book Deleted",result);
    }

}