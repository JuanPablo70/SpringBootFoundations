package com.juan.sanchez.factory;

import com.juan.sanchez.domain.Author;

import java.time.LocalDate;

public class AuthorFactory {

    public static Author create(Integer id) {
        Author author = new Author();
        author.setId(id);
        author.setName("Author");
        author.setLastname("From Client");
        author.setBirthday(LocalDate.of(1997, 11, 28));
        return author;
    }

    public static Author update(Author author) {
        author.setName(author.getName().toUpperCase());
        author.setLastname(author.getLastname().toUpperCase());
        return author;
    }

    private AuthorFactory() {}

}
