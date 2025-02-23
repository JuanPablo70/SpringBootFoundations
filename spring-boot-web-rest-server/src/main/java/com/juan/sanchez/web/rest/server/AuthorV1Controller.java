package com.juan.sanchez.web.rest.server;

import com.juan.sanchez.domain.Author;
import com.juan.sanchez.service.AuthorService;
import com.juan.sanchez.web.rest.utils.URIUtils;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Collection;

@RestController
@RequestMapping(path="/v1/authors")
class AuthorV1Controller {

    private final AuthorService authorService;

    AuthorV1Controller(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping(path="/{id}",
                produces= MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code= HttpStatus.OK)
    Author findOneById(@PathVariable(name="id") Integer id) {
        return authorService.findById(id);
    }

    @GetMapping(path="/{id}/books",
                produces= MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code= HttpStatus.OK)
    Author findOneByIdWithBooks(@PathVariable Integer id) {
        return authorService.findByIdWithBooks(id);
    }

    @GetMapping(produces= MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code= HttpStatus.OK)
    Collection<Author> findAll() {
        return authorService.findAll();
    }

    @DeleteMapping(path="/{id}")
    @ResponseStatus(code= HttpStatus.NO_CONTENT)
    void deleteOneById(@PathVariable Integer id) {
        authorService.delete(id);
    }

    @PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code= HttpStatus.CREATED)
    void save(@RequestBody Author author, HttpServletResponse response) {
        author = authorService.save(author);
        URI location = URIUtils.createURIForPost(author.getId());
        response.setHeader("location", location.toString());
    }

    @PutMapping(consumes=MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code=HttpStatus.NO_CONTENT)
    void update(@RequestBody Author author) {
        authorService.update(author);
    }

//    @ExceptionHandler({AuthorNotFoundException.class})
//	@ResponseStatus(code=HttpStatus.NOT_FOUND)
//    ErrorMessage handlerMappingFor404(Exception ex) {
//		return new ErrorMessage(ex.getMessage(), DateTimeUtils.currentDateTime());
//	}

}
