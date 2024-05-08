package com.blog_api.exceptions;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value = NullPointerException.class)
	public void NullPointerException() {
		
	}
	
	@ExceptionHandler(value = NumberFormatException.class)
	public void NumberFormatException() {
		
	}
}
