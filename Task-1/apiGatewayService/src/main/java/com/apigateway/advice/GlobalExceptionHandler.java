package com.apigateway.advice;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.MethodArgumentNotValidException;


@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleInvalidAruguments(MethodArgumentNotValidException ex) 
	 {
		Map<String, String> errorMap=new HashMap<>();

		ex.getBindingResult().getFieldErrors().forEach(error -> {
				errorMap.put(error.getField(), error.getDefaultMessage());
				
		});
		
		return errorMap;
	 }
	
	@ExceptionHandler(value = NoSuchElementException.class)
	public @ResponseBody ApiResponse handleException(NoSuchElementException ex)
	{
		
		return new ApiResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
		
	}
	
	@ExceptionHandler(value = HttpMessageNotReadableException.class)
	public ResponseEntity<Object> notReadable(HttpMessageNotReadableException ex)
	{
		
		return  new ResponseEntity<>("Invalid Data in Request Body!!!", HttpStatus.BAD_REQUEST);
		
	}
}
