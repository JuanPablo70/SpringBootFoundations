package com.juan.sanchez.service.impl;

import com.juan.sanchez.domain.Book;
import com.juan.sanchez.service.CacheService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
@Profile("cache")
class BookCacheServiceImpl implements CacheService<Book, Integer> {

    private final Map<Integer,Book> cache;

    BookCacheServiceImpl() {
        this.cache = new LinkedHashMap<>();
    }

    @Override
    public boolean add(Book book) {
        if(cache.get(book.getId()) != null) {
            return false;
        }
        else {
            cache.put(book.getId(), book);
            return true;
        }
    }

    @Override
    public Book get(Integer id) {
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
        }
        else {
            return false;
        }
    }

    @Override
    public void report() {
        System.out.println("Cache Report...");
        System.out.println(" Amount: " + cache.entrySet().size());
        for(Map.Entry<Integer,Book> entry : cache.entrySet()) {
            System.out.printf(" Key : %d%n", entry.getKey());
            System.out.printf(" Value: %s%n", entry.getValue());
        }
    }

}
