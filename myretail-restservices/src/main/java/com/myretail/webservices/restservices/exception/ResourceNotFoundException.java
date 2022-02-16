package com.myretail.webservices.restservices.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
	/**
	* 
	*/
	private static final long serialVersionUID = 1248324938263499061L;

	public ResourceNotFoundException(String message) {
		super(message);
		log.error(message);
	}

}
