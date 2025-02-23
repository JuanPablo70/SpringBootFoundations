package com.juan.sanchez.web.rest.server;

import com.juan.sanchez.domain.Author;
import com.juan.sanchez.service.AuthorService;
import com.juan.sanchez.web.rest.utils.URIUtils;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Collection;

@Controller
@RequestMapping(path="/v2/authors")
public class AuthorV2Controller {

    private final AuthorService authorService;

    AuthorV2Controller(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping(path="/{id}",
            produces= MediaType.APPLICATION_JSON_VALUE)
    HttpEntity<Author> findOneById(@PathVariable(name="id") Integer id) {
        return ResponseEntity.ok().body(authorService.findById(id));
    }

    @GetMapping(path="/{id}/books",
            produces= MediaType.APPLICATION_JSON_VALUE)
    HttpEntity<Author> findOneByIdWithBooks(@PathVariable Integer id) {
        return ResponseEntity.ok()
                .header("Country", "Colombia")
                .contentType(MediaType.APPLICATION_JSON)
                .body(authorService.findByIdWithBooks(id));
    }

    @GetMapping(produces= MediaType.APPLICATION_JSON_VALUE)
    HttpEntity<Collection<Author>> findAll() {
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(authorService.findAll());
    }

    @DeleteMapping(path="/{id}")
    HttpEntity<Void> deleteOneById(@PathVariable Integer id) {
        authorService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE)
    HttpEntity<Void> save(@RequestBody Author author) {
        author = authorService.save(author);
        URI location = URIUtils.createURIForPost(author.getId());
        return ResponseEntity.created(location).build();
    }

    @PutMapping(consumes=MediaType.APPLICATION_JSON_VALUE)
    HttpEntity<Void> update(@RequestBody Author author) {
        authorService.update(author);
        return ResponseEntity.noContent().build();
    }

//    @ExceptionHandler({AuthorNotFoundException.class})
//	@ResponseStatus(code=HttpStatus.NOT_FOUND)
//    ErrorMessage handlerMappingFor404(Exception ex) {
//		return new ErrorMessage(ex.getMessage(), DateTimeUtils.currentDateTime());
//	}

}
