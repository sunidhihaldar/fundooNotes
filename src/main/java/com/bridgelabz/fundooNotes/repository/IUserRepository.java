package com.bridgelabz.fundooNotes.repository;

import com.bridgelabz.fundooNotes.dto.UpdatePassword;
import com.bridgelabz.fundooNotes.model.UserEntity;

public interface IUserRepository {

	public UserEntity save(UserEntity user);
	
	public UserEntity getUser(String email);
	
	public UserEntity getUser(long userId);
	
	public boolean isVerified(long userId);
	
	public boolean updatePassword(UpdatePassword updatePassword, long userId);
}
