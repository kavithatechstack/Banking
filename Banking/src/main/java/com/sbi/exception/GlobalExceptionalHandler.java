package com.sbi.exception;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.UnexpectedTypeException;

@ControllerAdvice
public class GlobalExceptionalHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<Error> handleResourceNotFoundException(ResourceNotFoundException resouceNotFound,
			WebRequest webRequest) {
		Error error = new Error(resouceNotFound.getMessage(), webRequest.getDescription(false), HttpStatus.NOT_FOUND);
		return new ResponseEntity<Error>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ResourceAlreadyExsistException.class)
	public ResponseEntity<Error> handleResouceAlreadyExsistException(
			ResourceAlreadyExsistException resourceAlreadyExsist, WebRequest webRequest) {
		Error err = new Error(resourceAlreadyExsist.getMessage(), webRequest.getDescription(false),
				HttpStatus.CONFLICT);
		return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
	}

	 /* @ExceptionHandler(MethodArgumentNotValidException.class)
	  
			public ResponseEntity<Error> handleMethodArgumentNotValidException(
					MethodArgumentNotValidException methodArgumentNotException, WebRequest webRequest) {
			List<String> errorsList=  methodArgumentNotException.getFieldErrors().stream().map(field->field.getDefaultMessage()+" for "+field.getField()).toList();
			 
			Error err = new Error( errorsList.toString(),
						webRequest.getDescription(false), HttpStatus.NOT_ACCEPTABLE);
				return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
			}*/

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<Error> handleConstraintViolationException(
			ConstraintViolationException constraintViolationException, WebRequest webRequest) {
		Set<String> errorsList = constraintViolationException.getConstraintViolations().stream()
				.map(constraint -> constraint.getInvalidValue().toString()).collect(Collectors.toSet());

		Error err = new Error(errorsList.toString(), webRequest.getDescription(false), HttpStatus.NOT_ACCEPTABLE);
		return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(UnexpectedTypeException.class)
	public ResponseEntity<Error> handleUnexpectedTypeException(UnexpectedTypeException unexpectedTypeException,
			WebRequest webRequest) {

		Error err = new Error(unexpectedTypeException.getMessage(), webRequest.getDescription(false),
				HttpStatus.NOT_ACCEPTABLE);
		return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
	}

}
