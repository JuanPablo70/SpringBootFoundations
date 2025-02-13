package com.juan.sanchez.repository;

import com.juan.sanchez.domain.Author;

import java.util.Collection;

public interface AuthorRepository {

    Author save(Author author);
    Author findById(int id);
    Author findByIdWithBooks(int id);
    Collection<Author> findAll();
    Collection<Author> findAllWithBooks();
    long count();
    Author update(Author author);
    void delete(int id);

}
