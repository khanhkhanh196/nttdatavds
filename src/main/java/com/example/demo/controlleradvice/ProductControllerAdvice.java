package com.example.demo.controlleradvice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.demo.exception.ProductNotFoundException;
import com.example.demo.payload.response.ProductErrorResponse;

@ControllerAdvice
public class ProductControllerAdvice {
	
	@ExceptionHandler
	public ResponseEntity<ProductErrorResponse> handleException(ProductNotFoundException exc) {
		ProductErrorResponse error = new ProductErrorResponse(HttpStatus.NOT_FOUND.value(), exc.getMessage(),
				System.currentTimeMillis());

		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler
	public ResponseEntity<ProductErrorResponse> handleException(Exception exc) {
		ProductErrorResponse error = new ProductErrorResponse(HttpStatus.BAD_REQUEST.value(), exc.getMessage(),
				System.currentTimeMillis());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
}
