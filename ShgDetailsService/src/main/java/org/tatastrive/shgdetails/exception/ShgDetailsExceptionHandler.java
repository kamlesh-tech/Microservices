package org.tatastrive.shgdetails.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestControllerAdvice
public class ShgDetailsExceptionHandler {

	ErrorResponse response = new ErrorResponse();;
	private static final Logger logger = LoggerFactory.getLogger(ShgDetailsExceptionHandler.class);
	
// Hanlde Invalid Argument Exception
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleBeanValidationExcaption(MethodArgumentNotValidException ex, HttpServletRequest request){
		List<String> errorMessage =ex.getBindingResult()
							.getFieldErrors().stream()
							.map(error -> error.getField() + ": "+error.getDefaultMessage())
							.collect(Collectors.toList());
		
		logger.info("Error Message in Shg Details Field : "+ errorMessage);
		
		response.setStatus(HttpStatus.BAD_REQUEST.value());
		response.setPath(request.getServletPath());
		response.setErrorMessage(errorMessage);
		response.setTimestamp(new Date());
		return ResponseEntity.badRequest().body(response);
	}

// Global Exception Hanlder(If Any Exception Occure)
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?>globalExcepetionHander(Exception ex, HttpServletRequest request){
		List<String> errorMessage =List.of(ex.getMessage());

	logger.info("Error Message in Shg Details Field : "+ errorMessage);
	
	response.setStatus(HttpStatus.BAD_REQUEST.value());
	response.setPath(request.getServletPath());
	response.setErrorMessage(errorMessage);
	response.setTimestamp(new Date());
		return ResponseEntity.badRequest().body(response);
	}
	
}