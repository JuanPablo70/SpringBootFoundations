package com.juan.sanchez.web.rest.client;

import com.juan.sanchez.domain.Author;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.util.Collection;

@Component
public class AuthorWebClientV2 {

    private final WebClient webClient;

    AuthorWebClientV2(WebClient webClient) {
        this.webClient = webClient;
    }

    public ResponseEntity<Author> findById(Integer id) {
        return webClient.get()
                .uri("/v1/authors/{id}", id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .toEntity(Author.class)
                .block();
    }

    public ResponseEntity<Collection<Author>> findAll() {
        return webClient.get()
                .uri("/v1/authors")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .toEntity(new ParameterizedTypeReference<Collection<Author>>() {})//
                .block();
    }

    public ResponseEntity<Void> save(Author author) {
        return webClient.post()
                .uri("/v1/authors")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(author)
                .retrieve()
                .toEntity(Void.class)
                .block();
    }

    public ResponseEntity<Void> update(Author author) {
        return webClient.put()
                .uri("/v1/authors")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(author)
                .retrieve()
                .toEntity(Void.class)
                .block();
    }

    public ResponseEntity<Void> deleteById(Integer id) {
        return webClient.delete()
                .uri("/v1/authors/{id}", id)
                .retrieve()
                .toEntity(Void.class)
                .block();
    }

    public ResponseEntity<Author> findByIdStatus404(Integer id) {
        return webClient.get()
                .uri("/v1/authors/{id}", id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .toEntity(Author.class)
                .onErrorResume(WebClientResponseException.class, ex -> {
                    ProblemDetail problemDetail = ex.getResponseBodyAs(ProblemDetail.class);
                    System.out.println("  " + problemDetail.toString());
                    return Mono.empty();
                })
                .block() ;
    }

}
