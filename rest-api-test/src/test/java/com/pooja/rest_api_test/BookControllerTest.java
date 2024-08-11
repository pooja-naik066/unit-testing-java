package com.pooja.rest_api_test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
class BookControllerTest {

    @MockBean
    private BookService bookService;

    @InjectMocks
    private BookController bookController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAllBooks() throws Exception {
        Book book1=new Book(1,"Pretty Little Things",5);
        Book book2=new Book(2,"The Moral Compass",3);
        Book book3=new Book(3,"Atomic Habits",4);

        List<Book> bookList= Arrays.asList(book1,book2,book3);
        Mockito.when(bookService.getAllBooks()).thenReturn(bookList);

        mockMvc.perform(MockMvcRequestBuilders.get("/books"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(3))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].title").value("Pretty Little Things"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].title").value("Atomic Habits"));

        verify(bookService, times(1)).getAllBooks();
    }

    @Test
    void getBookById() throws Exception {
        Book book1=new Book(1,"Pretty Little Things",5);
        Mockito.when(bookService.getBookById(book1.getId())).thenReturn(book1);
        mockMvc.perform(MockMvcRequestBuilders.get("/books/1"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Pretty Little Things"));
    }

    @Test
    void saveBook() throws Exception {
        Book book1 = new Book(1, "Pretty Little Things", 5);

        // Mock the saveBook method to return the book after it's "saved"
        Mockito.when(bookService.saveBook(any(Book.class))).thenReturn(book1);
        mockMvc.perform(MockMvcRequestBuilders.post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\": \"Pretty Little Things\", \"rating\": 5}"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Pretty Little Things"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.rating").value(5));
    }

    @Test
    void updateBook() throws Exception {
        Book book1 = new Book(1, "Pretty Little Things", 3);
        Mockito.when(bookService.updateBook(any(Book.class))).thenReturn(book1);

        String jsonContent = "{\"id\": 1, \"title\": \"Pretty Little Things\", \"rating\": 3}";
        //System.out.println("JSON Content: " + jsonContent);

        mockMvc.perform(MockMvcRequestBuilders.put("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Pretty Little Things"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.rating").value(3));
    }

    @Test
    void deleteBook() throws Exception {
        int bookId = 1;
        Mockito.when(bookService.deleteBook(bookId)).thenReturn("Book Deleted");

        mockMvc.perform(MockMvcRequestBuilders.delete("/books/{id}", bookId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Book Deleted"));

        Mockito.verify(bookService, Mockito.times(1)).deleteBook(bookId);
    }

}