package com.bridgelabz.fundooNotes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundooNotes.dto.UserDto;
import com.bridgelabz.fundooNotes.response.Response;
import com.bridgelabz.fundooNotes.service.IUserService;

@RestController
@RequestMapping("user")
@CrossOrigin(origins = "http://localhost:8080")
public class UserController {

	@Autowired
	private IUserService service;
	
	public ResponseEntity<Response> registration(@RequestBody UserDto userDto) {
		boolean result = service.registration(userDto);
		if(!result)
			return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body(new Response("user exists", 400));
		return ResponseEntity.status(HttpStatus.CREATED).body(new Response("registration successful", 200));
	}
}
