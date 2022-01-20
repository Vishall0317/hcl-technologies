package com.hcl.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ValidationErrorResponse> handleException(MethodArgumentNotValidException ex){
		List<FieldError> errors=ex.getFieldErrors();
		var validationErrorResponse = new ValidationErrorResponse();
		validationErrorResponse.setDateTime(LocalDateTime.now());
		validationErrorResponse.setStatuscode(HttpStatus.BAD_REQUEST.value());
		validationErrorResponse.setMessage("Input data has some errors...please input correct data");

		for(FieldError fieldError: errors) {
			validationErrorResponse.getErrors().put(fieldError.getField(), fieldError.getDefaultMessage());
		}
		return new ResponseEntity<> (validationErrorResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ValidationErrorResponse> handleException(ConstraintViolationException ex){

		var validationErrorResponse = new ValidationErrorResponse();
		validationErrorResponse.setDateTime(LocalDateTime.now());
		validationErrorResponse.setStatuscode(HttpStatus.BAD_REQUEST.value());
		validationErrorResponse.setMessage("Input data has some errors...please input correct data");

		ex.getConstraintViolations().forEach(error->
				validationErrorResponse.getErrors().put("field", error.getMessage())
		);
		return new ResponseEntity<> (validationErrorResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<ErrorResponse> handleException(NotFoundException ex){

		var errorResponse =new ErrorResponse();
		errorResponse.setDateTime(LocalDateTime.now());
		errorResponse.setStatuscode(HttpStatus.NOT_FOUND.value());
		errorResponse.setMessage(ex.getMessage());
		
		return new ResponseEntity<> (errorResponse, HttpStatus.NOT_FOUND);
		
	}	
}
