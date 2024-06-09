package com.example.genaiservice.common;

public class CustomException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private final String message;

	public CustomException(String message) {
		super(message);
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
