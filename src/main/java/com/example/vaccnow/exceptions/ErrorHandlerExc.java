package com.example.vaccnow.exceptions;

import java.io.Serializable;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonInclude;

import org.modelmapper.MappingException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class ErrorHandlerExc {

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

	@ExceptionHandler({ MethodArgumentNotValidException.class })
	public ResponseEntity<?> handleConstraintViolation(MethodArgumentNotValidException ex) {

		log.error("{}", ex.getGlobalErrors());
		log.error("{}", ex.getFieldErrors());
		log.error("{}", ex.getGlobalErrors());
		log.error("{}", ex.getBindingResult().getFieldErrors());

		var errors = ex.getBindingResult().getFieldErrors().stream()
				.map(err -> new ErrorMessage(err.getField(), err.getDefaultMessage())).collect(Collectors.toList());
		log.error("{}", errors);
		return new ResponseEntity<>(errors, new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}
}

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
class ErrorMessage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer statusCode;
	private String message;

	private @NonNull String property;
	private @NonNull String error;
}

@Getter
@AllArgsConstructor
class DataValidationException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private final String property;
	private final String message;
}
