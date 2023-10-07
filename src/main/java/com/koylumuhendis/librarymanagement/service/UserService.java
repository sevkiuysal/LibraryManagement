package com.koylumuhendis.librarymanagement.service;


import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.koylumuhendis.librarymanagement.dto.UserDto;
import com.koylumuhendis.librarymanagement.exception.GenericException;
import com.koylumuhendis.librarymanagement.model.User;
import com.koylumuhendis.librarymanagement.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

	private final UserRepository userRepository;


	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}



		@Transactional(rollbackFor =  Exception.class )
	public User createUser(User user) {

		return userRepository.save(user);
	}
	
	public User findByUsername(String username) {
		return userRepository.findUserByUsername(username)
				.orElseThrow(() -> new GenericException.builder()
						.httpStatus(HttpStatus.NOT_FOUND)
						.errorMessage("user not found given id!")
						.build());
	}
	public UserDto getUser(String username) {
		User user=findByUsername(username);
		return new UserDto.builder()
				.username(user.getUsername())
				.role(user.getRole())
				.email(user.getEmail())
				.build();
	}
	
}
