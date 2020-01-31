package com.bridgelabz.fundooNotes.service;

import org.springframework.stereotype.Component;

import com.bridgelabz.fundooNotes.dto.LoginDetails;
import com.bridgelabz.fundooNotes.dto.UpdatePassword;
import com.bridgelabz.fundooNotes.dto.UserDto;
import com.bridgelabz.fundooNotes.model.UserEntity;
/**
 * This interface provides unimplemented methods for user service class
 * @author Sunidhi Haldar
 * @created 2020-01-17
 * @version 1.8
 */

@Component
public interface IUserService {

	public boolean registration(UserDto userDto);
	
	public boolean isVerified(String token);
	
	public UserEntity login(LoginDetails login);
	
	public boolean updatePassword(String token, UpdatePassword passwordInfo);
	
	public boolean isUserAvailable(String email);
	
}
