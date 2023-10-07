package com.koylumuhendis.librarymanagement.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.koylumuhendis.librarymanagement.dto.TokenResponseDto;
import com.koylumuhendis.librarymanagement.request.LoginRequest;
import com.koylumuhendis.librarymanagement.service.AuthService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("api/auth")
public class AuthController {
	
	private final AuthService authService;
	
	
	public AuthController(AuthService authService) {
		this.authService = authService;
	}



	@PostMapping("login")
	public ResponseEntity<TokenResponseDto> login(@RequestBody LoginRequest loginRequest){
		return ResponseEntity.ok(authService.login(loginRequest));
	}

}
