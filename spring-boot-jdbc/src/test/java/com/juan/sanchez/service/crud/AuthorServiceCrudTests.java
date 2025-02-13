package com.juan.sanchez.service.crud;

import com.juan.sanchez.domain.Author;
import com.juan.sanchez.exception.AuthorEmptyCollectionException;
import com.juan.sanchez.exception.AuthorNotFoundException;
import com.juan.sanchez.service.AuthorService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
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
class AuthorServiceCrudTests {

    @Autowired
    private AuthorService authorService;

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
        Collection<Author> authors = authorService.findAll();
        assertNotNull(authors);
        assertFalse(authors.isEmpty());
        assertEquals(10, authors.size());
        System.out.println(" Testing 'findAll' - Pre Insert - End");
    }

    @Test
    @Order(2)
    @DisplayName("Testing 'count'- Pre Insert")
    void countPreInsertTest() {
        System.out.println(" Testing 'count'- Pre Insert- Start");
        long count = authorService.count();
        assertEquals(10, count);
        System.out.println(" Testing 'count' - Pre Insert - End");

    }

    @Test
    @Order(3)
    @DisplayName("Testing 'save' with 'id' 11")
    void saveWithId11Test() {
        System.out.println(" Testing 'save' with 'id' 11 - Start");
        Author author = new Author();
        author.setId(11);
        author.setName("Charles");
        author.setLastname("Daniels");
        author.setBirthday(LocalDate.of(1997, 11, 28));
        author = authorService.save(author);
        assertEquals(11, author.getId());
        System.out.println(" Testing 'save' with 'id' 11 - End");
    }

    @Test
    @Order(4)
    @DisplayName("Testing 'findAll' - Post Insert")
    void findAllPostInsertTest() {
        System.out.println(" Testing 'findAll' - Post Insert - Start");
        Collection<Author> authors = authorService.findAll();
        assertNotNull(authors);
        assertFalse(authors.isEmpty());
        assertEquals(11, authors.size());
        System.out.println(" Testing 'findAll' - Post Insert - End");
    }

    @Test
    @Order(5)
    @DisplayName("Testing 'count' - Post Insert")
    void countPostInsertTest() {
        System.out.println(" Testing 'count' - Post Insert - Start");
        long count = authorService.count();
        assertEquals(11, count);
        System.out.println(" Testing 'count' - Post Insert - End");
    }

    @Test
    @Order(6)
    @DisplayName("Testing 'findById' with 'id' 11 - Pre Update")
    void findByIdWithId11PreUpdateTest() {
        System.out.println(" Testing 'findById' with 'id' 11 - Pre Update - Start");
        Author author = authorService.findById(11);
        assertAll("Entity 'id' 11",

                () -> assertNotNull(author),
                () -> assertEquals("Charles", author.getName()),
                () -> assertEquals("Daniels", author.getLastname())

        );
        System.out.println(" Testing 'findById' with 'id' 11 - Pre Update - End");
    }

    @Test
    @Order(7)
    @DisplayName("Testing 'update' with 'id' 11")
    void updateWithId11Test() {
        System.out.println(" Testing 'update' with 'id' 11 - Start");
        Author author = authorService.findById(11);
        author.setName("Christopher");
        author.setLastname("Hughes");
        author = authorService.update(author);
        assertNotNull(author);
        assertEquals("Christopher", author.getName());
        assertEquals("Hughes", author.getLastname());
        System.out.println(" Testing 'update' with 'id' 11 - End");
    }

    @Test
    @Order(8)
    @DisplayName("Testing 'findById' with 'id' 11 - Post Update")
    void findByIdWithId11PostUpdateTest() {
        System.out.println(" Testing 'findById' with 'id' 11 - Post Update - Start");
        Author author = authorService.findById(11);
        assertAll("Entity 'id' 11",

                () -> assertNotNull(author),
                () -> assertEquals("Christopher", author.getName()),
                () -> assertEquals("Hughes", author.getLastname())

        );
        System.out.println(" Testing 'findById' with 'id' 11 - Post Update - End");
    }

    @Test
    @Order(9)
    @DisplayName("Testing 'findAll' - Pre Delete")
    void findAllPreDeleteTest() {
        System.out.println(" Testing 'findAll' - Pre Delete - Start");
        Collection<Author> authors = authorService.findAll();
        assertNotNull(authors);
        assertFalse(authors.isEmpty());
        assertEquals(11, authors.size());
        System.out.println(" Testing 'findAll' - Pre Delete - End");
    }

    @Test
    @Order(10)
    @DisplayName("Testing 'count' - Pre Delete")
    void countPreDeleteTest() {
        System.out.println(" Testing 'count' - Pre Delete - Start");
        long count = authorService.count();
        assertEquals(11, count);
        System.out.println(" Testing 'count' - Pre Delete - End");
    }

    @Test
    @Order(11)
    @DisplayName("Testing 'delete' with 'id' 11")
    void deleteWithId11Test() {
        System.out.println(" Testing 'delete' with 'id' 11 - Start");
        authorService.delete(11);
        System.out.println(" Testing 'delete' with 'id' 11 - End");
    }

    @Test
    @Order(12)
    @DisplayName("Testing 'findAll' - Post Delete")
    void findAllPostDeleteTest() {
        System.out.println(" Testing 'findAll' - Post Delete - Start");
        Collection<Author> authors = authorService.findAll();
        assertNotNull(authors);
        assertFalse(authors.isEmpty());
        assertEquals(10, authors.size());
        System.out.println(" Testing 'findAll' - Post Delete - End");
    }

    @Test
    @Order(13)
    @DisplayName("Testing 'count' - Post Delete")
    void countPostDeleteTest() {
        System.out.println(" Testing 'count' - Post Delete - Start");
        long count = authorService.count();
        assertEquals(10, count);
        System.out.println(" Testing 'count' - Post Delete - End");
    }

    @Rollback
    @Test
    @Order(14)
    @DisplayName("Testing 'findById' with 'id' 11")
    void findByIdWithId11Test() {
        System.out.println(" Testing 'findById' with 'id' 11 - Start");
        Throwable throwable =

                assertThrows(AuthorNotFoundException.class, () -> authorService.findById(11));

        assertEquals("Author 'id' 11 not found", throwable.getMessage());
        System.out.println(" Testing 'findById' with 11 - End");
    }

    @Rollback(true)
    @Test
    @Order(15)
    @Sql(statements={"DELETE FROM book","DELETE FROM author"},
            executionPhase= Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @DisplayName("Testing 'findAll' with Data Empty")
    void findAllWithDataEmptyTest() {
        System.out.println(" Testing 'findAll' with Data Empty - Start");
        Throwable throwable = assertThrows(AuthorEmptyCollectionException.class, () -> authorService.findAll());
        assertEquals("There are no authors", throwable.getMessage());
        System.out.println(" Testing 'findAll' with Data Empty - End");
    }

}
