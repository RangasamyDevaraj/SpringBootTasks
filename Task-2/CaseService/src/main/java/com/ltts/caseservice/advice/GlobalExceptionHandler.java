package com.ltts.caseservice.advice;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;



@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value= NoSuchElementException.class)
	public ResponseEntity<Object> noSuchElementFound(NoSuchElementException ex){
		
		return new ResponseEntity<>("User not found",HttpStatus.NOT_FOUND);
	}
	
}
