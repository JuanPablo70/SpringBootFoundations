package com.juan.sanchez.service.impl;

import com.juan.sanchez.domain.Author;
import com.juan.sanchez.service.CacheService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Profile("cache")
public class AuthorCacheServiceImpl implements CacheService<Author, Integer> {

    private final Map<Integer,Author> cache;

    AuthorCacheServiceImpl(Map<Integer, Author> cache) {
        this.cache = cache;
    }

    @Override
    public boolean add(Author author) {
        if(cache.get(author.getId()) != null) {
            return false;
        } else {
            cache.put(author.getId(), author);
            return true;
        }
    }

    @Override
    public Author get(Integer id) {
        if(cache.get(id) != null) {
            return cache.get(id);
        }
        return null;
    }

    @Override
    public boolean delete(Integer id) {
        if(cache.get(id) != null) {
            cache.remove(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void report() {
        System.out.println("Author Cache Report...");
        System.out.println(" Amount: " + cache.entrySet().size());
        for (Map.Entry<Integer, Author> entry: cache.entrySet()) {
            System.out.printf(" Key : %d%n", entry.getKey());
            System.out.printf(" Value: %s%n", entry.getValue());
        }
    }
}
