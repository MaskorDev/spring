package com.TrueXz.spring.book.controller;

import com.TrueXz.spring.base.exception.ResourceNotFoundException;
import com.TrueXz.spring.book.entity.BookEntity;
import com.TrueXz.spring.book.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BookApiController {
    private final BookService bookService;

    public BookApiController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/")
    public String ok() {
        return "ok";
    }
    @GetMapping("/api/v1/book")
    public List<BookEntity> all() {
        return bookService.all();
    }
    @GetMapping("/api/v1/book/{id}")
    public BookEntity byId(@PathVariable Integer id) {
        return bookService.byId(id).orElseThrow(ResourceNotFoundException::new);
    }

    @PostMapping("/api/v1/book")
    public BookEntity create(@RequestBody BookEntity request) {
        return bookService.create(request.getTitle(), request.getDescription());
    }

    @PutMapping("/api/v1/book/{id}")
    public BookEntity update(@PathVariable Integer id ,@RequestBody BookEntity request) {
        return bookService.update(request).orElseThrow(ResourceNotFoundException::new);
    }

    @DeleteMapping("/api/v1/book/{id}")
    public Boolean delete(@PathVariable("id") Integer id) {
        return bookService.delete(id);
    }
}
