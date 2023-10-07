package com.koylumuhendis.librarymanagement.service;

import com.koylumuhendis.librarymanagement.dto.TokenResponseDto;
import com.koylumuhendis.librarymanagement.request.LoginRequest;
import com.koylumuhendis.librarymanagement.utils.TokenGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class AuthServiceTest extends BaseServiceTest {

    private UserService userService;

    private TokenGenerator tokenGenerator;

    private AuthenticationManager authenticationManager;

    private AuthService authService;

    @BeforeEach
    void setup(){
        userService= Mockito.mock(UserService.class);
        tokenGenerator=Mockito.mock(TokenGenerator.class);
        authenticationManager=Mockito.mock(AuthenticationManager.class);
        authService=new AuthService(userService,tokenGenerator,authenticationManager);
    }

    @Test
    void itShouldLogin_WhenGivenLoginRequest(){
        LoginRequest loginRequest=new LoginRequest();
        loginRequest.setUsername("username");
        loginRequest.setPassword("password");

        Authentication authentication=authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken
                        (loginRequest.getUsername(), loginRequest.getPassword()));
        TokenResponseDto tokenResponseDto=new TokenResponseDto();

        tokenResponseDto=new TokenResponseDto(
                tokenGenerator.generateToken(authentication),
                userService.getUser(loginRequest.getUsername()));

    }
}