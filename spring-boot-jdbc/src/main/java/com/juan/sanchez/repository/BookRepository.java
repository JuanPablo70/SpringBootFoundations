package com.juan.sanchez.repository;

import com.juan.sanchez.domain.Book;

import java.util.Collection;

public interface BookRepository {

    Book save(Book book);
    Book findById(int id);
    Collection<Book> findAll();
    long count();
    Book update(Book book);
    void delete(int id);

}
