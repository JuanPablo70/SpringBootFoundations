package com.juan.sanchez.web.rest.client;

import com.juan.sanchez.domain.Author;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;

@Component
public class AuthorRestClientV3 {

    private final RestTemplate restTemplate;

    AuthorRestClientV3(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<Author> findById(Integer id) {
        return restTemplate.exchange("/v1/authors/{id}",
                HttpMethod.GET,
                null,
                Author.class,
                id);
    }

    public ResponseEntity<Collection<Author>> findAll() {
        return restTemplate.exchange("/v1/authors",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Collection<Author>>() {});
    }

    public ResponseEntity<Void> save(Author author) {
        return null;
    }

    public ResponseEntity<Void> update(Author author) {
        return null;
    }

    public ResponseEntity<Void> deleteById(Integer id) {
        return restTemplate.exchange("/v1/authors/{id}",
                HttpMethod.DELETE,
                null,
                Void.class,
                id);
    }

}
