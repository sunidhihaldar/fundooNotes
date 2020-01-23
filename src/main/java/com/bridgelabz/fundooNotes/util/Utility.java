package com.bridgelabz.fundooNotes.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Utility {

	public static String dateTime() {
		LocalDateTime current  = LocalDateTime.now();
		DateTimeFormatter format =  DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		String formatDateTime = current.format(format);
		return formatDateTime;
	}
	
	public static String passwordEncoder(String message) {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-256");
			md.update(message.getBytes());
			byte[] digest = md.digest();
			System.out.println(digest);
			StringBuffer hexString = new StringBuffer();
			for(int i = 0; i < digest.length; i++) {
				//hexString.append(Integer.toHexString(0xFF & digest[i]));
				hexString.append(String.format("%02X", digest[i]));
			}
			System.out.println("Hex format: " + hexString.toString());
			return hexString.toString();
		} catch (NoSuchAlgorithmException e) {
			System.out.println("Error message " + e.getMessage());
		}
		return null;
	}
	
	public static boolean matches(String dbPassword, String userPassword) {
		if(dbPassword.equals(userPassword)) {
			return true;
		}
		return false;
	}
}
