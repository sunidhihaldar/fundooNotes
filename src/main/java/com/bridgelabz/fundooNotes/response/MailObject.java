package com.bridgelabz.fundooNotes.response;

//Response class for login
public class MailObject {
 
	private String tokenCode;
	private int statusCode;
	
	public MailObject(String tokenCode, int statusCode) {
		super();
		this.tokenCode = tokenCode;
		this.statusCode = statusCode;
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
}
