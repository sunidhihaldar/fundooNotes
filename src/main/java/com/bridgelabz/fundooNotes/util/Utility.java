package com.bridgelabz.fundooNotes.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Utility {

	public static String dateTime() {
		LocalDateTime current  = LocalDateTime.now();
		DateTimeFormatter format =  DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		String formatDateTime = current.format(format);
		return formatDateTime;
	}
	
//	public static boolean matches(String dbPassword, String userPassword) {
//		if(dbPassword.equals(userPassword)) {
//			return true;
//		}
//		return false;
//	}
}
