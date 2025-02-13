package com.juan.sanchez.repository.jdbc;

import com.juan.sanchez.domain.Book;
import com.juan.sanchez.repository.BookRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.*;
import java.util.Collection;

@Repository
@Transactional
class BookJdbcRepository implements BookRepository {

    private final JdbcTemplate jdbcTemplate;

    BookJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Book save(Book book) {
        String sql = "INSERT INTO book (title, isbn, edition, publish_date, chapters, pages,author_id)" +
                "VALUES(?,?,?,?,?,?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, book.getTitle());
                ps.setString(2, book.getIsbn());
                ps.setInt(3, book.getEdition());
                ps.setDate(4, Date.valueOf(book.getPublishDate()));
                ps.setInt(5, book.getChapters());
                ps.setInt(6, book.getPages());
                ps.setInt(7, book.getAuthorId());
                return ps;
            }
        }, keyHolder);
        book.setId(keyHolder.getKey().intValue());
        return book;
    }

    @Override
    public Book findById(int id) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM book WHERE id=?", bookRowMapper, id);
        }
        catch(EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public Collection<Book> findAll() {
        return jdbcTemplate.query("SELECT * FROM book", bookRowMapper);
    }

    @Override
    public long count() {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM book", Long.class);
    }

    @Override
    public Book update(Book book) {
        jdbcTemplate.update("UPDATE book SET title=?, isbn=? WHERE id=?",
                book.getTitle(),
                book.getIsbn(),
                book.getId());
        return book;
    }

    @Override
    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM book WHERE id=?", id);
    }

    private boolean isResultSetEmpty(ResultSet rs) throws SQLException {
        return (!rs.isBeforeFirst() && rs.getRow()==0);
    }

    /**
     * Maps data from a `ResultSet` to a `Book` object.
     */
    RowMapper<Book> bookRowMapper = (rs, rowNum) -> {
        Book book = null;
        if(!isResultSetEmpty(rs)) {
            book = new Book();
            book.setId(rs.getInt("id"));
            book.setTitle(rs.getString("title"));
            book.setIsbn(rs.getString("isbn"));
            book.setEdition(rs.getInt("edition"));
            book.setPublishDate(rs.getDate("publish_date").toLocalDate());
            book.setChapters(rs.getInt("chapters"));
            book.setPages(rs.getInt("pages"));
            return book;
        } else {
            return book;
        }
    };

}
