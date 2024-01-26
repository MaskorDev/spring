package com.TrueXz.spring.book.service;

import com.TrueXz.spring.book.entity.BookEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BookService {
    static List<BookEntity> bookStorage = new ArrayList<>();

    public BookService() {
        fillStorage();
    }


    public void fillStorage() {
        Random random = new Random();
        for (int i = 0; i <= 100; i++) {
            BookEntity book = new BookEntity();
            book.setId(i);
            book.setTitle("Book #" + random.nextInt(100, 999));
            book.setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do " +
                    "eiusmod tempor incididunt ut labore et dolore magna aliqua.");
            bookStorage.add(book);
        }
    }

    public List<BookEntity> all() {
        return bookStorage;
    }

    public Optional<BookEntity> byId(Integer id) {
        return bookStorage.stream().filter(book -> book.getId().equals(id)).findFirst();
    }

    public BookEntity create(String title, String description) {
        BookEntity book = new BookEntity();
        book.setId(bookStorage.size());
        book.setTitle(title);
        book.setDescription(description);
        bookStorage.add(book);
        return book;
    }

    public Optional<BookEntity> update(BookEntity book) {
        Optional<BookEntity> oldBookOptional = byId(book.getId());
        if (oldBookOptional.isEmpty()) {
            return Optional.empty();
        }
        BookEntity oldBook = oldBookOptional.get();
        oldBook.setTitle(book.getTitle());
        oldBook.setDescription(book.getDescription());
        return Optional.of(oldBook);
    }

    public Boolean delete(Integer id) {
        Optional<BookEntity> book = byId(id);
        if (book.isEmpty()) {
            return false;
        }

        bookStorage.remove(book.get());
        return true;
    }


    public Optional<BookEntity> edit(Integer id, Map<String, String> fields) {
        Optional<BookEntity> optionalBookEntity = byId(id);
        if(optionalBookEntity.isEmpty()) {
            Optional.empty();
        }

        BookEntity book = optionalBookEntity.get();

        for (String key: fields.keySet()) {
            switch (key) {
                case "title" -> book.setTitle(fields.get(key));
                case "description" -> book.setDescription(fields.get(key));
            }
        }

        return Optional.of(book);
    }
}
