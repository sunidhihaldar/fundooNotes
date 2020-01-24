package com.bridgelabz.fundooNotes.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundooNotes.dto.LoginDetails;
//import com.bridgelabz.fundooNotes.customException.EmailAlreadyExistsException;
import com.bridgelabz.fundooNotes.dto.UserDto;
import com.bridgelabz.fundooNotes.model.UserEntity;
import com.bridgelabz.fundooNotes.repository.IUserRepository;
import com.bridgelabz.fundooNotes.response.MailResponse;
import com.bridgelabz.fundooNotes.util.JwtGenerator;
import com.bridgelabz.fundooNotes.util.MailServiceProvider;
import com.bridgelabz.fundooNotes.util.Utility;

//used to load user details from a database
@Service
public class UsersServiceImpl implements IUserService {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private IUserRepository userRepository;
	
	@Autowired
	private JwtGenerator generate;
	
	@Autowired
	private MailServiceProvider mailServiceProvider;

	@Override
	public boolean registration(UserDto userDto) {
		UserEntity fetchedUser = userRepository.getUser(userDto.getEmail());
		if(fetchedUser !=  null)
			return false;
		UserEntity user = new UserEntity();
		BeanUtils.copyProperties(userDto,  user);
		user.setCreatedAt(Utility.dateTime());
		String password = bCryptPasswordEncoder.encode(user.getPassword());
		user.setPassword(password);
		userRepository.save(user);
		String response = MailResponse.formMessage("http://localhost:8081/user/verification", generate.jwtToken(user.getUserId()));
		mailServiceProvider.sendEmail(user.getEmail(), "Rgistration  verification", response);
		return true;
	}

	@Override
	public boolean isVerified(String token) {
		long fetchedUserId = generate.parseJWT(token);
		userRepository.isVerified(fetchedUserId);
		return true;
	}

	@Override
	public UserEntity login(LoginDetails login) {
		UserEntity user = userRepository.getUser(login.getEmail());
		if(user != null) {
			if(user.isVerified() && bCryptPasswordEncoder.matches(login.getPassword(), user.getPassword()))
				return user;
			String response = MailResponse.formMessage("http://localhost:8081/user/verification", generate.jwtToken(user.getUserId()));
			mailServiceProvider.sendEmail(user.getEmail(), "login", response);
			return user;
		}
		return null;
	}
}
