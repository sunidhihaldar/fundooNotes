package com.bridgelabz.fundooNotes.customException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.bridgelabz.fundooNotes.response.Response;

/**
 * This class is the global response exception handler, helps to handle the custom exceptions
 * @author Sunidhi Haldar
 * @created 2020-01-31
 * @version 1.8
 */

@RestControllerAdvice
public class MyExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler({ UserNotVerifiedException.class, UserNotFoundException.class })
	public ResponseEntity<Response> userNotVerified(Exception ex) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(ex.getMessage(), 400));
	}

	@ExceptionHandler(NoteNotFoundException.class)
	public ResponseEntity<Response> noteException(NoteNotFoundException ex) {
		Response response = new Response(ex.getMessage(), 400);
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(LabelNotFoundException.class)
	public ResponseEntity<Response> labelException(LabelNotFoundException ex) {
		Response response = new Response(ex.getMessage(), 502);
		return new ResponseEntity<>(response, HttpStatus.BAD_GATEWAY);
	}
}