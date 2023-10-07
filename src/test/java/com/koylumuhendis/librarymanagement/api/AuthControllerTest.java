package com.koylumuhendis.librarymanagement.api;

import com.koylumuhendis.librarymanagement.dto.TokenResponseDto;
import com.koylumuhendis.librarymanagement.dto.UserDto;
import com.koylumuhendis.librarymanagement.request.LoginRequest;
import com.koylumuhendis.librarymanagement.service.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



class AuthControllerTest extends BaseControllerTest{


    private AuthService authService;
    @Autowired
    private MockMvc mockMvc;


    @BeforeEach
    void setup(){
        authService= Mockito.mock(AuthService.class);
    }


    @Test
    void itShouldLogin_WhenRequestBody() throws Exception {
        LoginRequest request=new LoginRequest();
        request.setUsername("username");
        UserDto userDto=new UserDto.builder()
                .username(request.getUsername())
                .build();
        TokenResponseDto response=new TokenResponseDto();
        response.setUserDto(userDto);

        when(authService.login(request)).thenReturn(response);
        ResultActions actions= mockMvc.perform(post("/api/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                ).andDo(print())
                .andExpect(status().isOk());
    }
}