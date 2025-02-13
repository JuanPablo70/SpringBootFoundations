package com.juan.sanchez.repository.jdbc;

import com.juan.sanchez.domain.Author;
import com.juan.sanchez.domain.Book;
import com.juan.sanchez.repository.AuthorRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

@Repository
@Transactional
class AuthorJdbcRepository implements AuthorRepository {

    private final JdbcTemplate jdbcTemplate;

    AuthorJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Author save(Author author) {
        jdbcTemplate.update("INSERT INTO author(id, name, lastname, birthday) VALUES(?,?,?,?)",
                author.getId(),
                author.getName(),
                author.getLastname(),
                author.getBirthday());
        return author;
    }

    @Override
    public Author findById(int id) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM author WHERE id=?", authorRowMapper, id);
        }
        catch(EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public Author findByIdWithBooks(int id) {
        try {
            return jdbcTemplate.query("SELECT a.id as id_author, b.id as id_book, a.*, b.* " +
                            "FROM author a " +
                            "INNER JOIN book b " +
                            "ON a.id=b.author_id " +
                            "WHERE a.id=?",
                    authorResultSetExtractor, id);
        }
        catch(EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public Collection<Author> findAll() {
        return jdbcTemplate.query("SELECT * FROM author", authorRowMapper);
    }

    @Override
    public Collection<Author> findAllWithBooks() {
        return jdbcTemplate.query("SELECT a.id as id_author, b.id as id_book, a.*, b.* " +
                        "FROM author a " +
                        "INNER JOIN book b " +
                        "ON a.id=b.author_id " +
                        "ORDER BY a.id, b.id ASC",
                AuthorsResultSetExtractor);
    }

    @Override
    public long count() {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM author", Long.class);
    }

    @Override
    public Author update(Author author) {
        jdbcTemplate.update("UPDATE author SET name=?, lastname=?, birthday=? WHERE id=?",
                author.getName(),
                author.getLastname(),
                author.getBirthday(),
                author.getId());
        return author;
    }

    @Override
    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM author WHERE id=?", id);
    }

    private boolean isResultSetEmpty(ResultSet rs) throws SQLException {
        return (!rs.isBeforeFirst() && rs.getRow()==0);
    }

    private Book mapBook(ResultSet rs) throws SQLException {
        Book book = new Book();
        book.setId(rs.getInt("id_book"));
        book.setTitle(rs.getString("title"));
        book.setIsbn(rs.getString("isbn"));
        book.setEdition(rs.getInt("edition"));
        book.setPublishDate(rs.getDate("publish_date").toLocalDate());
        book.setChapters(rs.getInt("chapters"));
        book.setPages(rs.getInt("pages"));
        return book;
    }

    /**
     * Maps data from a `ResultSet` to an `Author` object.
     */
    RowMapper<Author> authorRowMapper = (rs, rowNum) -> {
        Author author = null;
        if(!isResultSetEmpty(rs)) {
            author = new Author();
            author.setId(rs.getInt("id"));
            author.setName(rs.getString("name"));
            author.setLastname(rs.getString("lastname"));
            author.setBirthday(rs.getDate("birthday").toLocalDate());
            return author;
        } else {
            return author;
        }
    };

    /**
     * Extracts data from a `ResultSet` and maps it to an `Author` object with their books.
     */
    ResultSetExtractor<Author> authorResultSetExtractor = (rs) -> {
        Author author = null;
        if(!isResultSetEmpty(rs)) {
            while(rs.next()) {
                if(author == null) {
                    author = new Author();
                    author.setId(rs.getInt("id_author"));
                    author.setName(rs.getString("name"));
                    author.setLastname(rs.getString("lastname"));
                    author.setBirthday(rs.getDate("birthday").toLocalDate());
                }
                author.addBook(mapBook(rs));
            }
            return author;
        } else {
            return author;
        }
    };


    /**
     * Extracts data from a `ResultSet` and maps it to a set of authors with their books.
     */
    ResultSetExtractor<Set<Author>> AuthorsResultSetExtractor = (rs) -> {
        Set<Author> authors = new LinkedHashSet<>();
        if(!isResultSetEmpty(rs)) {
            int parent_id = 0;
            Author author = null;
            while(rs.next()) {
                if(parent_id != rs.getInt("id_author")) {
                    parent_id = rs.getInt("id_author");
                    author = null;
                }
                if(author == null) {
                    author = new Author();
                    author.setId(rs.getInt("id_author"));
                    author.setName(rs.getString("name"));
                    author.setLastname(rs.getString("lastname"));
                    author.setBirthday(rs.getDate("birthday").toLocalDate());
                    authors.add(author);
                }
                author.addBook(mapBook(rs));
            }
            return authors;
        }
        else {
            return authors;
        }
    };

}
