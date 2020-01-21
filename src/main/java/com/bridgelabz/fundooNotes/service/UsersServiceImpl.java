package com.bridgelabz.fundooNotes.service;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundooNotes.dto.UserDto;
import com.bridgelabz.fundooNotes.model.UserEntity;
import com.bridgelabz.fundooNotes.repository.IUserRepository;

@Service
public class UsersServiceImpl implements IUsersService {

	BCryptPasswordEncoder bCryptPasswordEncoder;
	IUserRepository userRepository;

	@Autowired
	public UsersServiceImpl(BCryptPasswordEncoder bCryptPasswordEncoder, IUserRepository userRepository) {
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.userRepository = userRepository;
	}
	
	// default method of UserDetailsService, locates user based on email
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserEntity userEntity = userRepository.findByEmail(email);
		if (userEntity == null) {
			throw new UsernameNotFoundException(email);
		}
		System.out.println(userEntity);
		return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), true, true, true, true,
				new ArrayList<>());
	}
	
	@Override
	public UserDto getUserByEmail(String email) {
		UserEntity userEntity = userRepository.findByEmail(email);
		if (userEntity == null) {
			throw new UsernameNotFoundException(email);
		}
		System.out.println(userEntity);
		// return new ModelMapper().map(userEntity, UserDto.class);
		return null;
	}

}
