package com.example.demo;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@SpringBootApplication
@RestController
public class Main {

    private List<Book> books = new ArrayList<>();

    public Main() {
        books.add(new Book(1, "1984", "George Orwell"));
        books.add(new Book(2, "To Kill a Mockingbird", "Harper Lee"));
    }

    @GetMapping("/books")
    public List<Book> getBooks() {
        return books;
    }

    @GetMapping("/books/{id}")
    public Book getBook(@PathVariable int id) {
        return books.stream().filter(b -> b.id == id).findFirst().orElse(null);
    }

    @PostMapping("/books")
    public Book addBook(@RequestBody Book book) {
        books.add(book);
        return book;
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    static class Book {
        public int id;
        public String title;
        public String author;

        public Book() {}
        public Book(int id, String title, String author) {
            this.id = id;
            this.title = title;
            this.author = author;
        }
    }
}




