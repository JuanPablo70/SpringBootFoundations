package com.juan.sanchez.web.rest.client;

import com.juan.sanchez.domain.Author;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;

@Component
public class AuthorRestClientV4 {

    private final RestTemplate restTemplate;

    AuthorRestClientV4(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<Author> findById(Integer id) {
        RequestEntity<Void> request = RequestEntity.get("/v1/authors/{id}", id)
                .accept(MediaType.APPLICATION_JSON)
                .build();
        return restTemplate.exchange(request, Author.class);
    }

    public ResponseEntity<Collection<Author>> findAll() {
        RequestEntity<Void> request = RequestEntity.get("/v1/authors")
                .accept(MediaType.APPLICATION_JSON)
                .build();
        return restTemplate.exchange(request, new ParameterizedTypeReference<Collection<Author>>() {});
    }

    public ResponseEntity<Void> save(Author author) {
        RequestEntity<Author> request = RequestEntity.post("/v1/authors")
                .accept(MediaType.APPLICATION_JSON)
                .body(author);
        return restTemplate.exchange(request, Void.class);
    }

    public ResponseEntity<Void> update(Author author) {
        RequestEntity<Author> request = RequestEntity.put("/v1/authors")
                .accept(MediaType.APPLICATION_JSON)
                .body(author);
        return restTemplate.exchange(request, Void.class);
    }

    public ResponseEntity<Void> deleteById(Integer id) {
        RequestEntity<Void> request = RequestEntity.delete("/v1/authors/{id}", id)
                .build();
        return restTemplate.exchange(request, Void.class);
    }

    public  ResponseEntity<Author> findByIdStatus404(Integer id) {
        try {
            RequestEntity<Void> request = RequestEntity.get("/v1/authors/{id}", id)
                    .accept(MediaType.APPLICATION_JSON)
                    .build();
            return restTemplate.exchange(request, Author.class);
        } catch (RestClientResponseException ex) {
            ProblemDetail problemDetail = ex.getResponseBodyAs(ProblemDetail.class);
            System.out.println("  " + problemDetail.toString());
            return null;
        }
    }

}
