package com.exercise.egym.domain;

public class HttpResponseObject {
	private String message;
	private int httpStatusCode;

	public HttpResponseObject(String  message, int httpStatusCode) {
		this.message = message;
		this.httpStatusCode = httpStatusCode;
	}
	public String getMessage() {
		return message;
	}
	public int getHttpStatusCode() {
		return httpStatusCode;
	}
	public void setHttpStatusCode(int httpStatusCode) {
		this.httpStatusCode = httpStatusCode;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
