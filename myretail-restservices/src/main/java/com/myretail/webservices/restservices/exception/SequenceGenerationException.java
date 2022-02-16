package com.myretail.webservices.restservices.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class SequenceGenerationException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7279983262760086405L;

	public SequenceGenerationException(String message) {
		super(message);
		log.error(message);
	}

}
