package com.github.AlissonMartin.ong.controllers;

import com.github.AlissonMartin.ong.models.User;
import com.github.AlissonMartin.ong.repositories.UserRepository;
import com.github.AlissonMartin.ong.services.TokenService;
import com.github.AlissonMartin.ong.services.UserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

@WebMvcTest(AuthenticationController.class)
class AuthenticationControllerIntegrationTest {


    @MockBean
    private UserService userService;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TokenService tokenService;

    @MockBean
    private UserRepository userRepository;


    @Test
    public void ShouldReturnOkIfUserIsRegistered() throws Exception {
        User mockUser = new User();
        mockUser.setEmail("test@gmail.com");
        Mockito.when(userRepository.findByEmail("test@gmail.com")).thenReturn(Optional.of(mockUser));

        mockMvc.perform(MockMvcRequestBuilders.post("/authentication/login")).andExpect(MockMvcResultMatchers.status().isOk());

    }
}