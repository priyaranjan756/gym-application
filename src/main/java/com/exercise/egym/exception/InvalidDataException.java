package com.exercise.egym.exception;

public class InvalidDataException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -456385504161334413L;

	public InvalidDataException(String message) {
		super(message);
	}
	public InvalidDataException(String message, Throwable throwable) {
		super(message,throwable);
	}

}
