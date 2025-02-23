package com.juan.sanchez.service.impl;

import com.juan.sanchez.domain.Book;
import com.juan.sanchez.exception.BookEmptyCollectionException;
import com.juan.sanchez.exception.BookNotFoundException;
import com.juan.sanchez.repository.BookRepository;
import com.juan.sanchez.service.BookService;
import com.juan.sanchez.service.CacheService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

@Service
@Transactional
class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final CacheService<Book, Integer> cacheService;

    BookServiceImpl(BookRepository bookRepository, CacheService<Book, Integer> cacheService) {
        this.bookRepository = bookRepository;
        this.cacheService = cacheService;
    }

    @Override
    public Book save(Book book) {
        book = bookRepository.save(book);
        cacheService.add(book);
        return book;
    }

    @Override
    public Book findById(int id) {
        if(cacheService.get(id) != null) {
            return cacheService.get(id);
        } else {
            Book book = null;
            book = bookRepository.findById(id)
                    .orElseThrow(() -> new BookNotFoundException("Book 'id' " + id + " not found"));

            cacheService.add(book);
            return book;
        }
    }

    @Override
    public Collection<Book> findAll() {
        return Optional.ofNullable(bookRepository.findAll())
                .filter(p -> !p.isEmpty())
                .orElseThrow(() -> new BookEmptyCollectionException("There are no books"));
    }

    @Override
    public long count() {
        return bookRepository.count();
    }

    @Override
    public Book update(Book book) {
        book = bookRepository.save(book);
        cacheService.delete(book.getId());
        cacheService.add(book);
        return book;
    }

    @Override
    public void delete(int id) {
        if(cacheService.get(id) != null) {
            bookRepository.deleteById(id);
            cacheService.delete(id);
        } else {
            bookRepository.deleteById(id);
        }
    }

    @Override
    public void report(Book book) {
        System.out.println("Book Report");
        System.out.println(book.toString());
    }

    @Override
    public void report(Collection<Book> books) {
        System.out.println("Book Reports...");
        for (Book book : books) {
            report(book);
        }
    }

    @Override
    public void report(long count) {
        System.out.println("Amount books report: " + count);
    }

    @Override
    public void report() {
        cacheService.report();
    }

}
