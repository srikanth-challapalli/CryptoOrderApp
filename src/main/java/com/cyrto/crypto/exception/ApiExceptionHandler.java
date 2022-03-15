package com.cyrto.crypto.exception;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value= {AsyncCallFailedException.class})
	public ResponseEntity<Object> handleAPIRequestException(AsyncCallFailedException exe){
		ApiException ex = new ApiException(exe.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, ZonedDateTime.now(ZoneId.of("z")));
		return new ResponseEntity<>(exe,HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
