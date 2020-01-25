package com.bridgelabz.fundooNotes.repository;

import com.bridgelabz.fundooNotes.dto.UpdatePassword;
import com.bridgelabz.fundooNotes.model.UserEntity;

/**
 * This interface provides unimplemented methods like insertion of user, updating password, getting a user
 * using email and id
 * @author Sunidhi Haldar
 * @created 2020-01-17
 * @version 1.8
 */

public interface IUserRepository {

	public UserEntity save(UserEntity user);
	
	public UserEntity getUser(String email);
	
	public UserEntity getUser(long userId);
	
	public boolean isVerified(long userId);
	
	public boolean updatePassword(UpdatePassword updatePassword, long userId);
}
