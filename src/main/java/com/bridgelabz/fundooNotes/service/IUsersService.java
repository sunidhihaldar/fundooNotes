package com.bridgelabz.fundooNotes.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import com.bridgelabz.fundooNotes.dto.UserDto;

//UserDetailsService interface is used to retrieve user-related data
public interface IUsersService extends UserDetailsService {

	public UserDto getUserByEmail(String email);
}
