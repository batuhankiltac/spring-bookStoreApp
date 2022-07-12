package com.batuhankiltac.bookstore.service;

import com.batuhankiltac.bookstore.entity.Book;
import com.batuhankiltac.bookstore.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    public Optional<Book> findBookById(Integer id) {
        return bookRepository.findById(id);
    }
}