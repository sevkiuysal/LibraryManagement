package com.koylumuhendis.librarymanagement.repository;

import com.koylumuhendis.librarymanagement.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserRepositoryTest extends BaseRepositoryTest{

    @Autowired
    private UserRepository userRepository;

    @Test
    void itShouldFindUser_WhenUsernameExist() throws Exception{
        String username="username";

        User expected=new User.builder()
                .username(username)
                .build();
        userRepository.save(expected);

        //Optional<User> actual = userRepository.findUserByUsername(username);
        User actual = userRepository.findUserByUsername(username).get();

        assertEquals(expected,actual);
    }
}