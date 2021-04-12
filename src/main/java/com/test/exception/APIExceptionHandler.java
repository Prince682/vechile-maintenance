package com.test.exception;

import java.util.HashMap;
import java.util.Map;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.test.util.ServiceResponse;

@ControllerAdvice
public class APIExceptionHandler {

	@ExceptionHandler(value = { ApiRequestException.class })
	public ResponseEntity<Object> handleAPIRequestException(ApiRequestException e) {
		HttpStatus httpStatus = e.getStatusCode();
		APIException apiException = new APIException(httpStatus, e.getMessage());
		return ResponseEntity.status(httpStatus).body(apiException);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public final ResponseEntity<Object> handleConstraintViolationExceptions(ConstraintViolationException ex) {
		String exceptionResponse = String.format("Invalid input parameters: %s\n", ex.getMessage());
		return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> methodErrorHandler(MethodArgumentNotValidException ex) {

		Map<String, String> map = new HashMap<>();

		ex.getBindingResult().getFieldErrors().forEach(error -> map.put(error.getField(), error.getDefaultMessage()));

		String exceptionResponse = String.format("Invalid input parameters", ex.getMessage());

		ServiceResponse<Map<String, String>> serviceResponse = new ServiceResponse<>();

		serviceResponse.setBusinessCode(HttpStatus.NOT_ACCEPTABLE.value());
		serviceResponse.setPayload(map);
		serviceResponse.setExtraDetails(exceptionResponse);

		return new ResponseEntity(serviceResponse, HttpStatus.NOT_ACCEPTABLE);

	}

}
