package com.koylumuhendis.librarymanagement.service;

import com.koylumuhendis.librarymanagement.exception.GenericException;
import com.koylumuhendis.librarymanagement.model.User;
import com.koylumuhendis.librarymanagement.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class UserServiceTest extends BaseServiceTest{

    private UserRepository userRepository;

    private UserService userService;



    @BeforeEach
    void setUp(){
        userRepository= mock(UserRepository.class);

        userService=new UserService(userRepository);
    }

    @Test
    void itShouldCreateUser(){
        //given
        User user=new User.builder()
                .password("password")
                .username("username")
                .build();

        //when
        when(userRepository.save(any(User.class))).thenReturn(user);
        //after
        User actual=userService.createUser(user);

        verify(userRepository).save(user);

        assertEquals(user,actual);
        assertEquals("username",actual.getUsername());
    }

    @Test
    void itShouldReturnUserbyGivenUsername_WhenUserExist(){
        //given
        String username="username";
        User expected=new User.builder()
                .username(username)
                .password("password")
                .build();

        //when
        when(userRepository.findUserByUsername(anyString())).thenReturn(Optional.of(expected));
        //after

        User actual = userService.findByUsername(username);

        verify(userRepository,times(1)).findUserByUsername(username);

        assertEquals(expected,actual);

    }

    @Test
    void itShouldThrowException_WhenUserNotExists(){
        //given
        GenericException expectedError=new GenericException.builder()
                .httpStatus(HttpStatus.NOT_FOUND)
                .errorMessage("user not found given id!")
                .build();

        //when

        when(userRepository.findUserByUsername(Mockito.anyString())).thenReturn(Optional.empty());
        GenericException actualError = assertThrows(GenericException.class,
                () -> userService.findByUsername(Mockito.anyString()));

        //then
        assertEquals(expectedError.getErrorMessage(),actualError.getErrorMessage());
    }
}