package com.test.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiRequestException extends Exception {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The status code. */
	private HttpStatus statusCode;

	public ApiRequestException() {
		super();
	}

	public ApiRequestException(String message) {
		super(message);
	}

	public ApiRequestException(HttpStatus code, String message) {
		super(message);
		this.statusCode = code;
	}

	public ApiRequestException(Throwable cause) {
		super(cause);
	}

	public ApiRequestException(String message, Throwable cause) {
		super(message, cause);
	}
}
