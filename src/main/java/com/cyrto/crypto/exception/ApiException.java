package com.cyrto.crypto.exception;

import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;

public class ApiException {
	
	private final String message;
	private final HttpStatus status;
	private final ZonedDateTime timeStamp;
	
	public ApiException(String message, HttpStatus status, ZonedDateTime timestamp) {
		this.timeStamp = timestamp;
		this.status = status;
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public ZonedDateTime getTimeStamp() {
		return timeStamp;
	}
	
	

}
