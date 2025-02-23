package com.juan.sanchez.web.rest.client;

import com.juan.sanchez.domain.Author;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;

@Component
public class AuthorRestClientV2 {

    private final RestTemplate restTemplate;

    AuthorRestClientV2(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<Author> findById(Integer id) {
        return restTemplate.getForEntity("/v1/authors/{id}", Author.class, id);
    }

    @SuppressWarnings({"unchecked","rawtypes"})
    public ResponseEntity<Collection<Author>> findAll() {
//        return restTemplate.getForObject("/v1/authors", Collection.class);
        return restTemplate.getForEntity("/v1/authors", (Class<Collection<Author>>) (Class) Collection.class);
    }

    public ResponseEntity<Void> save(Author author) {
        return restTemplate.postForEntity("/v1/authors", author, Void.class);
    }

    public ResponseEntity<Void> update(Author author) {
        return null;
    }

    public ResponseEntity<Void> deleteById(Integer id) {
        return null;
    }

}
