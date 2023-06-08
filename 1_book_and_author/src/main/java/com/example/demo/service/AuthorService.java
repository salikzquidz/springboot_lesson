package com.example.demo.service;

import com.example.demo.domain.Author;

public interface AuthorService {

    Iterable<Author> findAll();
}
