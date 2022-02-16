package com.myretail.webservices.restservices.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class MyRetailException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4296571980312369430L;

	public MyRetailException(String message) {
		super(message);
		log.error(message);
	}

}