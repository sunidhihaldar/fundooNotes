package com.bridgelabz.fundooNotes.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundooNotes.customException.UserNotFoundException;
import com.bridgelabz.fundooNotes.customException.UserNotVerifiedException;
import com.bridgelabz.fundooNotes.dto.LoginDetails;
import com.bridgelabz.fundooNotes.dto.UpdatePassword;
//import com.bridgelabz.fundooNotes.customException.EmailAlreadyExistsException;
import com.bridgelabz.fundooNotes.dto.UserDto;
import com.bridgelabz.fundooNotes.model.UserEntity;
import com.bridgelabz.fundooNotes.repository.IUserRepository;
import com.bridgelabz.fundooNotes.response.MailResponse;
import com.bridgelabz.fundooNotes.util.JwtGenerator;
import com.bridgelabz.fundooNotes.util.MailServiceProvider;
import com.bridgelabz.fundooNotes.util.Utility;

/**
 * This class loads user details from a database
 * @author Sunidhi Haldar
 * @created 2020-01-17
 * @version 1.8
 */

@Service
public class UserServiceImpl implements IUserService {

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
		if (fetchedUser != null)
			return false;
		UserEntity user = new UserEntity();
		BeanUtils.copyProperties(userDto, user);
		user.setCreatedAt(Utility.dateTime());
		String password = bCryptPasswordEncoder.encode(user.getPassword());
		user.setPassword(password);
		userRepository.save(user);
		String response = MailResponse.formMessage("http://localhost:8081/user/verification",
				generate.createJwtToken(user.getUserId()));
		mailServiceProvider.sendEmail(user.getEmail(), "Registration  verification", response);
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
		// fetching user
		UserEntity user = userRepository.getUser(login.getEmail());
		// checks if user is present
		if (user != null) {
			// if user is verified and encrypted raw password matches the already stored
			// encrypted password then returns user
			if (bCryptPasswordEncoder.matches(login.getPassword(), user.getPassword())) {
				if (user.isVerified())
					return user;
				// else sends a link
				String response = MailResponse.formMessage("http://localhost:8081/user/verification",
						generate.createJwtToken(user.getUserId()));
				mailServiceProvider.sendEmail(user.getEmail(), "Registration  verification", response);
				return null;
			}
			throw new UserNotVerifiedException("Invalid credentials");
		}
		// user does not exist
		throw new UserNotFoundException("User not found! ");
	}

	@Override
	public boolean updatePassword(String token, UpdatePassword passwordInfo) {
		if (passwordInfo.getNewPassword().equals(passwordInfo.getConfirmPassword())) {
			passwordInfo.setConfirmPassword(bCryptPasswordEncoder.encode(passwordInfo.getConfirmPassword()));
			userRepository.updatePassword(passwordInfo, generate.parseJWT(token));
		}
		return false;
	}

	@Override
	public boolean isUserAvailable(String email) {
		UserEntity isUserAvailable = userRepository.getUser(email);
		if (isUserAvailable != null) {
			if (isUserAvailable.isVerified()) {
				String response = MailResponse.formMessage("http://localhost:8081/user/updatePassword",
						generate.createJwtToken(isUserAvailable.getUserId()));
				mailServiceProvider.sendEmail(isUserAvailable.getEmail(), "Update password link", response);
				return true;
			}
			// if user is not verified
			String response = MailResponse.formMessage("http://localhost:8081/user/verification",
					generate.createJwtToken(isUserAvailable.getUserId()));
			mailServiceProvider.sendEmail(isUserAvailable.getEmail(), "Registration  verification", response);
			return false;
		}
		throw new UserNotFoundException("User not found");
	}
}