package com.bridgelabz.fundooNotes.service;

import org.springframework.stereotype.Component;

import com.bridgelabz.fundooNotes.dto.LoginDetails;
import com.bridgelabz.fundooNotes.dto.UpdatePassword;
import com.bridgelabz.fundooNotes.dto.UserDto;
import com.bridgelabz.fundooNotes.model.UserEntity;

//UserDetailsService interface is used to retrieve user-related data
@Component
public interface IUserService {

	public boolean registration(UserDto userDto);
	
	public boolean isVerified(String token);
	
	public UserEntity login(LoginDetails login);
	
	public boolean updatePassword(String token, UpdatePassword passwordInfo);
	
	public boolean isUserAvailable(String email);
	
}
