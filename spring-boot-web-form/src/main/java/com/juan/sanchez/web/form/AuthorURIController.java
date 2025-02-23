package com.juan.sanchez.web.form;

import com.juan.sanchez.domain.Author;
import com.juan.sanchez.service.AuthorService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/uri")
class AuthorURIController {

    private final AuthorService authorService;

    AuthorURIController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping(path={"/authors/{id}", "/authors/{id}.html"},
            produces= MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    String findOneById(Model model, @PathVariable(name="id") Integer id) {
        model.addAttribute("author", authorService.findById(id));
        return "author/findOne";
    }

    @GetMapping(path={"/authors/{id}/books", "/authors/{id}/books.html"},
            produces=MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    String findOneByIdWithBooks(Model model, @PathVariable Integer id) {
        model.addAttribute("author", authorService.findByIdWithBooks(id));
        return "author/findOneWithBooks";
    }

    @GetMapping(path={"/authors", "/authors.html"},
            produces=MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    String findAll(Model model) {
        model.addAttribute("authors", authorService.findAll());
        return "author/findAll";
    }

    @GetMapping(path="/authors/{id}/delete",
            produces=MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    String deleteOneById(Model model, @PathVariable Integer id) {
        model.addAttribute("author", authorService.findById(id));
        return "author/deleteOne";
    }

    @DeleteMapping(path="/author/delete")
    String deleteOneById(@ModelAttribute(name="author") Author author) {
        authorService.delete(author.getId());
        return "redirect:/uri/authors";
    }

//    @ExceptionHandler({AuthorNotFoundException.class})
//	ModelAndView handlerMappingFor404(Exception ex) {
//		ModelAndView mav = new ModelAndView();
//		mav.setViewName("general/error");
//		mav.addObject("errorMessage", ex.getMessage());
//		mav.addObject("currentDateTime", DateTimeUtils.currentDateTime());
//		return mav;
//	}

}
