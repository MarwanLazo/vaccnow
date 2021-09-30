package com.example.vaccnow.exceptions;

import java.io.Serializable;

import org.modelmapper.MappingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.Builder;
import lombok.Data;

@RestControllerAdvice
public class ErrorHandlerExc {
	@ExceptionHandler({ GroupNotFoundException.class })
	ResponseEntity<?> handler(GroupNotFoundException ex) {
		ex.printStackTrace();
		return new ResponseEntity<>(
				ErrorMessage.builder().statusCode(HttpStatus.BAD_REQUEST.value()).message(ex.getMessage()).build(),
				HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@ExceptionHandler({ Exception.class })
	ResponseEntity<?> handler(Exception ex) {
		ex.printStackTrace();
		return new ResponseEntity<>(
				ErrorMessage.builder().statusCode(HttpStatus.BAD_REQUEST.value()).message(ex.getMessage()).build(),
				HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@ExceptionHandler({ MappingException.class })
	ResponseEntity<?> handler(MappingException ex) {
		ex.printStackTrace();
		return new ResponseEntity<>(
				ErrorMessage.builder().statusCode(HttpStatus.BAD_REQUEST.value()).message(ex.getMessage()).build(),
				HttpStatus.INTERNAL_SERVER_ERROR);

	}

}

@Data
@Builder
class ErrorMessage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer statusCode;
	private String message;
}