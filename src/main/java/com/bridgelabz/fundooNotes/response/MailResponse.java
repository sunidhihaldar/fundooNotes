package com.bridgelabz.fundooNotes.response;

public class MailResponse {

	public static String formMessage(String url, String token) {
		return url + "/" + token;
	}
}
