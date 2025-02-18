package com.juan.sanchez.repository;

import com.juan.sanchez.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Integer> {

    @Query("SELECT a FROM Author a JOIN FETCH a.books WHERE a.id=:id")
    Optional<Author> findByIdWithBooks(@Param("id") int id);

    @Query("SELECT a,b FROM Author a INNER JOIN Book b ON a.id=b.authorId ORDER BY a.id, b.id ASC")
    Collection<Author> findAllWithBooks();
}
