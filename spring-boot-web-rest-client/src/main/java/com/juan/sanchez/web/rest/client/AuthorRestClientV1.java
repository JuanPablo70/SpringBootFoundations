package com.juan.sanchez.web.rest.client;

import com.juan.sanchez.domain.Author;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;

@Component
public class AuthorRestClientV1 {

    private final RestTemplate restTemplate;

    AuthorRestClientV1(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Author findById(Integer id) {
        return restTemplate.getForObject("/v1/authors/{id}", Author.class, id);
    }

    @SuppressWarnings({"unchecked","rawtypes"})
    public Collection<Author> findAll() {
//        return restTemplate.getForObject("/v1/authors", Collection.class);
        return restTemplate.getForObject("/v1/authors", (Class<Collection<Author>>) (Class) Collection.class);
    }

    public void save(Author author) {
        restTemplate.postForObject("/v1/authors", author, Void.class);
    }

    public void update(Author author) {
        restTemplate.put("/v1/authors", author);
    }

    public void deleteById(Integer id) {
        restTemplate.delete("/v1/authors/{id}", id);
    }

}
