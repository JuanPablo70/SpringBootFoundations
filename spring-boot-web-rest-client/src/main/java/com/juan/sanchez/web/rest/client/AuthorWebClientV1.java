package com.juan.sanchez.web.rest.client;

import com.juan.sanchez.domain.Author;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Collection;

@Component
public class AuthorWebClientV1 {

    private final WebClient webClient;

    AuthorWebClientV1(WebClient webClient) {
        this.webClient = webClient;
    }

    public Author findById(Integer id) {
        return webClient.get()
                .uri("/v1/authors/{id}", id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Author.class)
                .block();
    }

    public Collection<Author> findAll() {
        return webClient.get()
                .uri("/v1/authors")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<Collection<Author>>() {})//
                .block();
    }

    public void save(Author author) {
        webClient.post()
                .uri("/v1/authors")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(author)
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }

    public void update(Author author) {
        webClient.put()
                .uri("/v1/authors")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(author)
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }

    public void deleteById(Integer id) {
        webClient.delete()
                .uri("/v1/authors/{id}", id)
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }

}
