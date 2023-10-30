package org.tatastrive.memberdetails.exception;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class MemberExceptionHandler {
	
	private static final Logger logger = LoggerFactory.getLogger(MemberExceptionHandler.class);
	MemberErrorResponse response = new MemberErrorResponse();

// Handle Invalid Argument exception in class....
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleInvalidMethodArugument(MethodArgumentNotValidException ex, HttpServletRequest request){
		
		List<String> errorMessage =  ex.getBindingResult().getFieldErrors()
									.stream().map(error -> error.getField() + ": "+ error.getDefaultMessage() )
									.collect(Collectors.toList());

		logger.info("Error Message in Member Details Field : "+ errorMessage);
		response.setStatus(HttpStatus.BAD_REQUEST.value());
		response.setPath(request.getServletPath());
		response.setErrorMessage(errorMessage);
		response.setTimestamp(new Date());
		return ResponseEntity.badRequest().body(response);
											
	}

// Handling all Exception Occurs in Service.....
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleGlobalResponse(Exception ex , HttpServletRequest request){
		List<String> errorMessage =List.of(ex.getMessage());

		logger.info("Error Message in Member Details Field : "+ errorMessage);
		
		response.setStatus(HttpStatus.BAD_REQUEST.value());
		response.setPath(request.getServletPath());
		response.setErrorMessage(errorMessage);
		response.setTimestamp(new Date());
		return ResponseEntity.badRequest().body(response);
	}
}
