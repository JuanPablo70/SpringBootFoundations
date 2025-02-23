package com.juan.sanchez.web.form;

import com.juan.sanchez.exception.AuthorNotFoundException;
import com.juan.sanchez.utils.DateTimeUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice(basePackages="com.juan.sanchez.web.form")
public class WebFormControllerAdvice {

    @ExceptionHandler({AuthorNotFoundException.class})
    ModelAndView handlerMappingFor404(Exception ex) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("general/error");
        mav.addObject("errorMessage", ex.getMessage());
        mav.addObject("currentDateTime", DateTimeUtils.currentDateTime());
        return mav;
    }

}
