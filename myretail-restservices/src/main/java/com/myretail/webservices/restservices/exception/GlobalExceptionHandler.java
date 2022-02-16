package com.myretail.webservices.restservices.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleException(Exception ex) {
		log.error("Exception handler");
		ErrorResponse errorDetails = new ErrorResponse(LocalDateTime.now().toString(), ex.getMessage(), null);
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> handleResourceNotFoundException(Exception ex) {
		log.error("Exception handler");
		ErrorResponse errorDetails = new ErrorResponse(LocalDateTime.now().toString(), ex.getMessage(), null);
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(MyRetailException.class)
	public ResponseEntity<?> handleMyRetailException(Exception ex) {
		log.error("Exception handler");
		ErrorResponse errorDetails = new ErrorResponse(LocalDateTime.now().toString(), ex.getMessage(), null);
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
