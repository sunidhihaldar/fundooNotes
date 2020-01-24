package com.bridgelabz.fundooNotes.response;

//Response class for login
public class UserAuthenticationResponse {
 
	private String tokenCode;
	private int statusCode;
	private Object object;
	
	public UserAuthenticationResponse(String tokenCode, int statusCode, Object object) {
		super();
		this.tokenCode = tokenCode;
		this.statusCode = statusCode;
		this.object = object;
	}
	
	public String getTokenCode() {
		return tokenCode;
	}
	public void setTokenCode(String tokenCode) {
		this.tokenCode = tokenCode;
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
}
