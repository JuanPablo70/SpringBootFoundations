package com.juan.sanchez.service.impl;

import com.juan.sanchez.domain.Author;
import com.juan.sanchez.domain.Book;
import com.juan.sanchez.exception.AuthorEmptyCollectionException;
import com.juan.sanchez.exception.AuthorNotFoundException;
import com.juan.sanchez.repository.AuthorRepository;
import com.juan.sanchez.service.AuthorService;
import com.juan.sanchez.service.CacheService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

@Service
@Transactional
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final CacheService<Author, Integer> cacheService;

    AuthorServiceImpl(AuthorRepository authorRepository, CacheService<Author, Integer> cacheService) {
        this.authorRepository = authorRepository;
        this.cacheService = cacheService;
    }

    @Override
    public Author save(Author author) {
        author = authorRepository.save(author);
        cacheService.add(author);
        return author;
    }

    @Override
    public Author findById(int id) {
        if(cacheService.get(id) != null) {
            return cacheService.get(id);
        }
        else {
            Author author = null;
            author = Optional.ofNullable(authorRepository.findById(id))
                    .orElseThrow(() -> new AuthorNotFoundException("Author 'id' " + id + " not found"));
            cacheService.add(author);
            return author;
        }
    }

    @Override
    public Author findByIdWithBooks(int id) {
        return Optional.ofNullable(authorRepository.findByIdWithBooks(id))
                .orElseThrow(() -> new AuthorNotFoundException("Author 'id' " + id + " not found"));
    }

    @Override
    public Collection<Author> findAll() {
        return Optional.ofNullable(authorRepository.findAll())
                .filter(p -> !p.isEmpty())
                .orElseThrow(() -> new AuthorEmptyCollectionException("There are no authors"));
    }

    @Override
    public Collection<Author> findAllWithBooks() {
        return Optional.ofNullable(authorRepository.findAllWithBooks())
                .filter(p -> !p.isEmpty())
                .orElseThrow(() -> new AuthorEmptyCollectionException("There are no authors"));
    }

    @Override
    public long count() {
        return authorRepository.count();
    }

    @Override
    public Author update(Author author) {
        author = authorRepository.update(author);
        cacheService.delete(author.getId());
        cacheService.add(author);
        return author;
    }

    @Override
    public void delete(int id) {
        if(cacheService.get(id) != null) {
            authorRepository.delete(id);
            cacheService.delete(id);
        }
        else {
            authorRepository.delete(id);
        }
    }

    @Override
    public void report(Author author) {
        System.out.println("Author Report");
        System.out.println(author.toString());
    }

    @Override
    public void reportWithBooks(Author author) {
        System.out.println("Author with Books Report");
        System.out.println(author.toString());
        if (!author.getBooks().isEmpty()) {
            for (Book book: author.getBooks()) {
                System.out.println(" " + book.toString());
            }
        } else {
            System.out.println("Author has no books ...");
        }
    }

    @Override
    public void report(Collection<Author> authors) {
        System.out.println("Authors Report");
        for (Author author: authors) {
            report(author);
        }
    }

    @Override
    public void reportWithBooks(Collection<Author> authors) {
        System.out.println("Authors Report");
        for (Author author: authors) {
            reportWithBooks(author);
        }
    }

    @Override
    public void report(long count) {
        System.out.println("Amount of Authors: " + count);
    }

    @Override
    public void report() {
        cacheService.report();
    }
}
