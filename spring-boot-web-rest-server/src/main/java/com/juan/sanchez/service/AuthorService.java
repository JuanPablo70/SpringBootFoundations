package com.juan.sanchez.service;

import com.juan.sanchez.domain.Author;

import java.util.Collection;

public interface AuthorService {

    Author save(Author author);
    Author findById(int id);
    Author findByIdWithBooks(int id);
    Collection<Author> findAll();
    Collection<Author> findAllWithBooks();
    long count();
    Author update(Author author);
    void delete(int id);

    void report(Author author);
    void reportWithBooks(Author author);
    void report(Collection<Author> authors);
    void reportWithBooks(Collection<Author> authors);
    void report(long count);

    void report();

}
