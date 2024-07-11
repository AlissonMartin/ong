package com.github.AlissonMartin.ong.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.AlissonMartin.ong.dtos.LoginRequestDTO;
import com.github.AlissonMartin.ong.dtos.RegisterRequestDTO;
import com.github.AlissonMartin.ong.enums.Role;
import com.github.AlissonMartin.ong.models.User;
import com.github.AlissonMartin.ong.repositories.UserRepository;
import com.github.AlissonMartin.ong.services.TokenService;
import com.github.AlissonMartin.ong.services.UserService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Optional;

import static net.bytebuddy.matcher.ElementMatchers.is;

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

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    public void setup()
    {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }


    @Test
    public void ShouldReturnOkIfUserIsRegistered() throws Exception {
        User mockUser = new User();
        mockUser.setEmail("test@gmail.com");

        Mockito.when(userRepository.findByEmail("test@gmail.com")).thenReturn(Optional.of(mockUser));
        Mockito.when(tokenService.generateToken(mockUser)).thenReturn("mockToken");

        LoginRequestDTO loginRequestDTO = new LoginRequestDTO("test@gmail.com", "123");

        mockMvc.perform(MockMvcRequestBuilders.post("/authentication/login").content(objectMapper.writeValueAsString(loginRequestDTO))
                .contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content().string("mockToken"));

    }

    @Test
    public void shouldReturnNotFoundIfUserIsNotFound() throws Exception {

        Mockito.when(userRepository.findByEmail("test@gmail.com")).thenReturn(Optional.empty());


        LoginRequestDTO loginRequestDTO = new LoginRequestDTO("test@gmail.com", "123");

        mockMvc.perform(MockMvcRequestBuilders.post("/authentication/login").content(objectMapper.writeValueAsString(loginRequestDTO)).contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void shouldReturnOkIfUserIsCreated() throws Exception {
        RegisterRequestDTO registerRequestDTO = new RegisterRequestDTO("name", "test@gmail.com", "123", "999.999.999-00", Role.USER);

        User newUser = new User();

        newUser.setName(registerRequestDTO.name());
        newUser.setEmail(registerRequestDTO.email());
        newUser.setPassword(registerRequestDTO.password());
        newUser.setFederalTaxId(registerRequestDTO.federalTaxId());
        newUser.setRole(registerRequestDTO.role());

        Mockito.when(userService.create(registerRequestDTO)).thenReturn(newUser);
        Mockito.when(tokenService.generateToken(newUser)).thenReturn("mockToken");

        mockMvc.perform(MockMvcRequestBuilders.post("/authentication/register").content(objectMapper.writeValueAsString(registerRequestDTO)).contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void shouldReturnUserDataAndToken() throws Exception {
        RegisterRequestDTO registerRequestDTO = new RegisterRequestDTO("name", "test@gmail.com", "123", "999.999.999-00", Role.USER);

        User newUser = new User();

        newUser.setName(registerRequestDTO.name());
        newUser.setEmail(registerRequestDTO.email());
        newUser.setPassword(registerRequestDTO.password());
        newUser.setFederalTaxId(registerRequestDTO.federalTaxId());
        newUser.setRole(registerRequestDTO.role());

        System.out.println(newUser.getRole());

        Mockito.when(userService.create(registerRequestDTO)).thenReturn(newUser);
        Mockito.when(tokenService.generateToken(newUser)).thenReturn("mockToken");

        mockMvc.perform(MockMvcRequestBuilders.post("/authentication/register").content(objectMapper.writeValueAsString(registerRequestDTO)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("name"))
                .andExpect(MockMvcResultMatchers.jsonPath("email").value("test@gmail.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("password").doesNotExist())
                .andExpect(MockMvcResultMatchers.jsonPath("federalTaxId").value("999.999.999-00"))
                .andExpect(MockMvcResultMatchers.jsonPath("role").value("USER"))
                .andExpect(MockMvcResultMatchers.jsonPath("token").value("mockToken"));
    }
}