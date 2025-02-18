package com.juan.sanchez.repository;

import com.juan.sanchez.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {

}
