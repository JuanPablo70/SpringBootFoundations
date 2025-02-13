package com.juan.sanchez.service.impl;

import com.juan.sanchez.domain.Book;
import com.juan.sanchez.service.CacheService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("no-cache")
public class BookNotFunctionalCacheServiceImpl implements CacheService<Book, Integer> {

    @Override
    public boolean add(Book book) {
        return false;
    }

    @Override
    public Book get(Integer id) {
        return null;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public void report() {
        System.out.println("Cache Report...");
        System.out.println(" There's no cache system");
    }

}
