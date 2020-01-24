package com.bridgelabz.fundooNotes.util;

import java.io.UnsupportedEncodingException;

import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

@Component
public class JwtGenerator {

	private static final String SECRET = "123456789";

	// generate JWT token
	public String createJwtToken(long l) {
		String token = null;
		try {
			token = JWT.create().withClaim("id", 1).sign(Algorithm.HMAC512(SECRET));
		} catch (IllegalArgumentException | JWTCreationException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return token;
	}

	// method to parse jwt token into integer
	public int parseJWT(String jwt) {
		Integer userId = 0;
		if (jwt != null) {
			try {
				userId = JWT.require(Algorithm.HMAC512(SECRET)).build().verify(jwt).getClaim("id").asInt();
			} catch (JWTVerificationException | IllegalArgumentException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return userId;
	}

//	public static void main(String[] args) {
//		JwtGenerator jw = new JwtGenerator();
//		String id = jw.JwtToken(10);
//		System.out.println(id);
//	}
}
