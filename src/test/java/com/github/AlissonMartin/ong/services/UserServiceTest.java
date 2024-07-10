package com.github.AlissonMartin.ong.services;

import com.auth0.jwt.algorithms.Algorithm;
import com.github.AlissonMartin.ong.dtos.RegisterRequestDTO;
import com.github.AlissonMartin.ong.enums.Role;
import com.github.AlissonMartin.ong.models.User;
import com.github.AlissonMartin.ong.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserServiceTest {

  @Mock
  UserRepository userRepository;

  @Mock
  PasswordEncoder passwordEncoder;

  @Mock
  Algorithm algorithm;

  @InjectMocks
  UserService userService;


  @BeforeEach
  void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  void create() {
    User mockUser = new User();
    mockUser.setEmail("test@gmail.com");
    mockUser.setPassword("123");

    RegisterRequestDTO registerRequestDTO = new RegisterRequestDTO("test", "test@gmail.com", "123", "999.999.999-00", Role.USER);

    when(passwordEncoder.encode(registerRequestDTO.password())).thenReturn("123");
    when(userRepository.save(any(User.class))).thenReturn(mockUser);

    User user = userService.create(registerRequestDTO);

    assertEquals(user.getEmail(), registerRequestDTO.email());
    assertEquals(user.getPassword(), registerRequestDTO.password());


  }
}