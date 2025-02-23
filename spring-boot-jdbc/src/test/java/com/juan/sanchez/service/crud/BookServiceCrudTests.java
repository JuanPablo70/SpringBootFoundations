package com.juan.sanchez.service.crud;

import com.juan.sanchez.domain.Book;
import com.juan.sanchez.exception.BookNotFoundException;
import com.juan.sanchez.service.BookService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

@Commit
@Transactional
@ActiveProfiles({"mysql","no-cache"})
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.NONE)
@DisplayName("Testing All CRUD Test Methods according with a specific order")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BookServiceCrudTests {

    @Autowired
    private BookService bookService;

    @BeforeAll
    static void beforeAll(@Autowired ApplicationContext ctx) throws SQLException {
        try(Connection conn = ctx.getBean(DataSource.class).getConnection()){
            String[] profiles = ctx.getEnvironment().getActiveProfiles();
            System.out.printf(" Active Profiles: %s%n", Arrays.asList(profiles));
            if(Arrays.asList(profiles).contains("mysql")) {
                ScriptUtils.executeSqlScript(conn, new ClassPathResource("/com/juan/sanchez/mysql/schema-mysql.sql"));
                ScriptUtils.executeSqlScript(conn, new ClassPathResource("/com/juan/sanchez/mysql/data-mysql.sql"));
            } else if(Arrays.asList(profiles).contains("h2")) {
                ScriptUtils.executeSqlScript(conn, new ClassPathResource("/com/juan/sanchez/h2/schema-h2.sql"));
                ScriptUtils.executeSqlScript(conn, new ClassPathResource("/com/juan/sanchez/h2/data-h2.sql"));
            } else {
                throw new RuntimeException("There are NO neither 'mysql' nor 'h2' profiles declared");
            }
        }
    }

    @Test
    @Order(1)
    @DisplayName("Testing 'findAll' - Pre Insert")
    void findAllPreInsertTest() {
        System.out.println(" Testing 'findAll' - Pre Insert - Start");
        Collection<Book> books = bookService.findAll();
        assertNotNull(books);
        assertFalse(books.isEmpty());
        assertEquals(18, books.size());
        System.out.println(" Testing 'findAll' - Pre Insert - End");
    }

    @Test
    @Order(2)
    @DisplayName("Testing 'count'- Pre Insert")
    void countPreInsertTest() {
        System.out.println(" Testing 'count'- Pre Insert- Start");
        long count = bookService.count();
        assertEquals(18, count);
        System.out.println(" Testing 'count' - Pre Insert - End");

    }

    @Test
    @Order(3)
    @DisplayName("Testing 'save' with 'id' 19")
    void saveWithId19Test() {
        System.out.println(" Testing 'save' with 'id' 19 - Start");
        Book book = new Book();
        book.setTitle("Programming in Something");
        book.setIsbn("1234567890");
        book.setEdition(1);
        book.setPublishDate(LocalDate.of(2000, 01, 01));
        book.setChapters(1000);
        book.setAuthorId(10);
        book = bookService.save(book);
        assertEquals(19, book.getId());
        System.out.println(" Testing 'save' with 'id' 19 - End");
    }

    @Test
    @Order(4)
    @DisplayName("Testing 'findAll' - Post Insert")
    void findAllPostInsertTest() {
        System.out.println(" Testing 'findAll' - Post Insert - Start");
        Collection<Book> books = bookService.findAll();
        assertNotNull(books);
        assertFalse(books.isEmpty());
        assertEquals(19, books.size());
        System.out.println(" Testing 'findAll' - Post Insert - End");
    }

    @Test
    @Order(5)
    @DisplayName("Testing 'count' - Post Insert")
    void countPostInsertTest() {
        System.out.println(" Testing 'count' - Post Insert - Start");
        long count = bookService.count();
        assertEquals(19, count);
        System.out.println(" Testing 'count' - Post Insert - End");
    }

    @Test
    @Order(6)
    @DisplayName("Testing 'findById' with 'id' 19 - Pre Update")
    void findByIdWithId19PreUpdateTest() {
        System.out.println(" Testing 'findById' with 'id' 19 - Pre Update - Start");
        Book book = bookService.findById(19);
        assertAll("Entity 'id' 19",

                () -> assertNotNull(book),
                () -> assertEquals("Programming in Something", book.getTitle()),
                () -> assertEquals("1234567890", book.getIsbn()),
                () -> assertEquals(1, book.getEdition())

        );
        System.out.println(" Testing 'findById' with 'id' 19 - Pre Update - End");
    }

    @Test
    @Order(7)
    @DisplayName("Testing 'update' with 'id' 19")
    void updateWithId19Test() {
        System.out.println(" Testing 'update' with 'id' 19 - Start");
        Book book = bookService.findById(19);
        book.setTitle("Programming in Something ABC");
        book.setIsbn("1234567890AAA");
        book = bookService.update(book);
        assertNotNull(book);
        assertEquals("Programming in Something ABC", book.getTitle());
        assertEquals("1234567890AAA", book.getIsbn());
        System.out.println(" Testing 'update' with 'id' 19 - End");
    }

    @Test
    @Order(8)
    @DisplayName("Testing 'findById' with 'id' 19 - Post Update")
    void findByIdWithId19PostUpdateTest() {
        System.out.println(" Testing 'findById' with 'id' 19 - Post Update - Start");
        Book book = bookService.findById(19);
        assertAll("Entity 'id' 19",

                () -> assertNotNull(book),
                () -> assertEquals("Programming in Something ABC", book.getTitle()),
                () -> assertEquals("1234567890AAA", book.getIsbn()),
                () -> assertEquals(1, book.getEdition())

        );
        System.out.println(" Testing 'findById' with 'id' 19 - Post Update - End");
    }

    @Test
    @Order(9)
    @DisplayName("Testing 'findAll' - Pre Delete")
    void findAllPreDeleteTest() {
        System.out.println(" Testing 'findAll' - Pre Delete - Start");
        Collection<Book> books = bookService.findAll();
        assertNotNull(books);
        assertFalse(books.isEmpty());
        assertEquals(19, books.size());
        System.out.println(" Testing 'findAll' - Pre Delete - End");
    }

    @Test
    @Order(10)
    @DisplayName("Testing 'count' - Pre Delete")
    void countPreDeleteTest() {
        System.out.println(" Testing 'count' - Pre Delete - Start");
        long count = bookService.count();
        assertEquals(19, count);
        System.out.println(" Testing 'count' - Pre Delete - End");
    }

    @Test
    @Order(11)
    @DisplayName("Testing 'delete' with 'id' 19")
    void deleteWithId19Test() {
        System.out.println(" Testing 'delete' with 'id' 19 - Start");
        bookService.delete(19);
        System.out.println(" Testing 'delete' with 'id' 19 - End");
    }

    @Test
    @Order(12)
    @DisplayName("Testing 'findAll' - Post Delete")
    void findAllPostDeleteTest() {
        System.out.println(" Testing 'findAll' - Post Delete - Start");
        Collection<Book> books = bookService.findAll();
        assertNotNull(books);
        assertFalse(books.isEmpty());
        assertEquals(18, books.size());
        System.out.println(" Testing 'findAll' - Post Delete - End");
    }

    @Test
    @Order(13)
    @DisplayName("Testing 'count' - Post Delete")
    void countPostDeleteTest() {
        System.out.println(" Testing 'count' - Post Delete - Start");
        long count = bookService.count();
        assertEquals(18, count);
        System.out.println(" Testing 'count' - Post Delete - End");
    }

    @Rollback
    @Test
    @Order(14)
    @DisplayName("Testing 'findById' with 'id' 19")
    void findByIdWithId19Test() {
        System.out.println(" Testing 'findById' with 'id' 19 - Start");
        Throwable throwable =

                assertThrows(BookNotFoundException.class, () -> bookService.findById(19));

        assertEquals("Book 'id' 19 not found", throwable.getMessage());
        System.out.println(" Testing 'findById' with 19 - End");
    }

}
