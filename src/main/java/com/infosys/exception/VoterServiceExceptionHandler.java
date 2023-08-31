package com.infosys.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.infosys.entity.Error;

@ControllerAdvice
public class VoterServiceExceptionHandler {

	@ExceptionHandler(value = InvalidVoterException.class)
	public ResponseEntity handleInvalidVoterException(Exception e) {
		System.out.println("Inside InvalidVoterException handler..!!");
		System.out.println("Invalid voter exception Occurred..!!");
		
		System.out.println(e.getMessage());
		
		Error err = new Error();
		err.setErrorMessage(e.getMessage());
		err.setHttpStatus(HttpStatus.BAD_REQUEST);
		
		return new ResponseEntity (err, err.getHttpStatus());
		
	}
}
