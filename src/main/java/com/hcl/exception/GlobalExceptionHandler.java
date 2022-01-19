package com.hcl.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<ErrorResponse> handleException(NotFoundException ex){

		var errorResponse =new ErrorResponse();
		errorResponse.setDateTime(LocalDateTime.now());
		errorResponse.setStatuscode(HttpStatus.NOT_FOUND.value());
		errorResponse.setMessage(ex.getMessage());
		
		return new ResponseEntity<> (errorResponse, HttpStatus.NOT_FOUND);
		
	}	
}
