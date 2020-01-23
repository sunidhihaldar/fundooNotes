package com.bridgelabz.fundooNotes.service;

import org.springframework.stereotype.Component;

import com.bridgelabz.fundooNotes.dto.UserDto;

//UserDetailsService interface is used to retrieve user-related data
@Component
public interface IUserService {

	public boolean registration(UserDto userDto);
	
	public boolean isVerified(String token);
}
