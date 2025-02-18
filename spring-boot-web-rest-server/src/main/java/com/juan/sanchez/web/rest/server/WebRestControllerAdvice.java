package com.juan.sanchez.web.rest.server;

import com.juan.sanchez.exception.AuthorNotFoundException;
import com.juan.sanchez.utils.DateTimeUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice(basePackages="com.juan.sanchez.web.rest.server")
class WebRestControllerAdvice extends ResponseEntityExceptionHandler {

	@ExceptionHandler({AuthorNotFoundException.class})
	ProblemDetail handlerMappingFor404(Exception ex) {
		ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
		problemDetail.setTitle("Resource requested does not exist");
		problemDetail.setDetail(ex.getMessage());
		problemDetail.setProperty("dateTime", DateTimeUtils.currentDateTime());
		return problemDetail;
	}

}
