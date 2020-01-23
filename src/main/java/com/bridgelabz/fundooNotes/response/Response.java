package com.bridgelabz.fundooNotes.response;

import java.util.List;

public class Response {

	private String message;
	
	private int statusCode;
	
	private Object object;
	
	List<String> details;

	public Response(String message, int statusCode, Object object) {
		super();
		this.message = message;
		this.statusCode = statusCode;
		this.object = object;
	}

	public Response(String message, int statusCode) {
		super();
		this.message = message;
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	public List<String> getDetails() {
		return details;
	}

	public void setDetails(List<String> details) {
		this.details = details;
	}
}
