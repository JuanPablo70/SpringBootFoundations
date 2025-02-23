package com.juan.sanchez.web.form;

import com.juan.sanchez.domain.Author;
import com.juan.sanchez.service.AuthorService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/url")
class AuthorURLController {

    private final AuthorService authorService;

    AuthorURLController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping(path={"/author", "/author.html"},
                produces=MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    String findOneById(Model model, @RequestParam(name="id") Integer id) {
        model.addAttribute("author", authorService.findById(id));
        return "author/findOne";
    }

    @GetMapping(path={"/author/books", "/author/books.html"},
            produces=MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    String findOneByIdWithBooks(Model model, @RequestParam Integer id) {
        model.addAttribute("author", authorService.findByIdWithBooks(id));
        return "author/findOneWithBooks";
    }

    @GetMapping(path={"/authors", "/authors.html"},
            produces=MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    String findAll(Model model) {
        model.addAttribute("authors", authorService.findAll());
        return "author/findAll";
    }

    @GetMapping(path="/author/delete",
            produces=MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    String deleteOneById(Model model, @RequestParam Integer id) {
        model.addAttribute("author", authorService.findById(id));
        return "author/deleteOne";
    }

    @DeleteMapping(path="/author/delete")
    String deleteOneById(@ModelAttribute(name="author") Author author) {
        authorService.delete(author.getId());
        return "redirect:/url/authors";
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
