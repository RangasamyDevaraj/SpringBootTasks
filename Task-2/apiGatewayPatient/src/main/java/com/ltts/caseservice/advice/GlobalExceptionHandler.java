package com.ltts.caseservice.advice;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ltts.caseservice.exception.InvalidCustomeExcetion;
import com.ltts.caseservice.exception.UserNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleInvalidAruguments(MethodArgumentNotValidException ex) 
	 {
		
		Map<String, String> errorMap=new HashMap<>();

		ex.getBindingResult().getFieldErrors().forEach(error -> {
				errorMap.put(error.getField(), error.getDefaultMessage());
				
		});
		System.out.println(errorMap);
		return errorMap;
	 }
	
	@ExceptionHandler(value = NoSuchElementException.class)
	public ApiResponse noSuchElementFoundException(NoSuchElementException ex)
	{
		return new ApiResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
	}
	
	@ExceptionHandler(value = UserNotFoundException.class)
	public @ResponseBody ApiResponse userNotFound(UserNotFoundException ex)
	{
		return new ApiResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
	}
	
	@ExceptionHandler(value = HttpRequestMethodNotSupportedException.class )
	 public ResponseEntity<Object> exception(HttpRequestMethodNotSupportedException exception)
	 {
		 return new ResponseEntity<>("please change httpmethod!!", HttpStatus.METHOD_NOT_ALLOWED);
	 }
	@ExceptionHandler(value = InvalidCustomeExcetion.class )
	public ApiResponse exception(InvalidCustomeExcetion exception)
	{
		return new ApiResponse(HttpStatus.NOT_FOUND.value(),"Invalid Details");
	}
	
	
	
}
