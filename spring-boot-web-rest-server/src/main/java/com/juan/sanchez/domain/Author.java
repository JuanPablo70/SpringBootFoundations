package com.juan.sanchez.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="author")
public class Author implements Serializable {

    @Id
    @Column(name="id", nullable=false)
    private int id;

    @Column(name="name", length=20, nullable=false)
    private String name;

    @Column(name="lastname", length=20, nullable=false)
    private String lastname;

    @Column(name="birthday", nullable=false)
    private LocalDate birthday;

    @JsonBackReference
    @OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.REMOVE, orphanRemoval=true)
    @JoinColumn(name="author_id", updatable=false)
    @OrderBy("id")
    private final Set<Book> books = new LinkedHashSet<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public void addBook(Book book) {
        book.setAuthorId(id);
        books.add(book);
    }

    public Set<Book> getBooks() {
        return books;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return id == author.id && Objects.equals(name, author.name) && Objects.equals(lastname, author.lastname) && Objects.equals(birthday, author.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, lastname, birthday);
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}
