package com.example.demo.service;

import com.example.demo.domain.Book;

public interface BookService {
    Iterable<Book> findAll();
}
