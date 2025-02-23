package com.juan.sanchez.service.impl;

import com.juan.sanchez.domain.Author;
import com.juan.sanchez.service.CacheService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("no-cache")
public class AuthorNotFunctionalCacheServiceImpl implements CacheService<Author, Integer> {


    @Override
    public boolean add(Author author) {
        return false;
    }

    @Override
    public Author get(Integer integer) {
        return null;
    }

    @Override
    public boolean delete(Integer integer) {
        return false;
    }

    @Override
    public void report() {
        System.out.println("Author Cache Report...");
        System.out.println(" There's no cache system");
    }
}
