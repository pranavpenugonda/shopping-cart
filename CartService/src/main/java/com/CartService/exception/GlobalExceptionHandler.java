package com.CartService.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import feign.FeignException;

@ControllerAdvice
public class GlobalExceptionHandler {


	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<String> handleGenericException(ProductNotFoundException ex) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<String> handleGenericException(UserNotFoundException ex) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
