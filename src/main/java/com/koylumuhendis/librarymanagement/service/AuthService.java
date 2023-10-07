package com.koylumuhendis.librarymanagement.service;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.koylumuhendis.librarymanagement.dto.TokenResponseDto;
import com.koylumuhendis.librarymanagement.exception.GenericException;
import com.koylumuhendis.librarymanagement.request.LoginRequest;
import com.koylumuhendis.librarymanagement.utils.TokenGenerator;

@Service
public class AuthService {

	private final UserService userService;
	
	private final TokenGenerator tokenGenerator;
	
	private final AuthenticationManager authenticationManager;
	
	
	public AuthService(UserService userService, TokenGenerator tokenGenerator,
			AuthenticationManager authenticationManager) {
		this.userService = userService;
		this.tokenGenerator = tokenGenerator;
		this.authenticationManager = authenticationManager;
	}


	public TokenResponseDto login(LoginRequest loginRequest) {
		try {
			Authentication authentication=authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
			TokenResponseDto tokenResponseDto=new TokenResponseDto(
					tokenGenerator.generateToken(authentication),userService.getUser(loginRequest.getUsername()));
			return tokenResponseDto;
		} catch (Exception e) {
			throw new GenericException.builder()
			.httpStatus(HttpStatus.NOT_FOUND)
			.errorMessage("user not found!")
			.build();
		}
	}

}
