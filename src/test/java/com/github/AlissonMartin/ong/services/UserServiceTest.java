package com.github.AlissonMartin.ong.services;

import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.AlissonMartin.ong.dtos.*;
import com.github.AlissonMartin.ong.enums.Role;
import com.github.AlissonMartin.ong.exceptions.RecordNotFoundException;
import com.github.AlissonMartin.ong.models.User;
import com.github.AlissonMartin.ong.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
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
  @DisplayName("should create a user and return it")
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

  @Test
  @DisplayName("should return a list of users")
  public void listWithFilter() {
    int page = 0;
    int size = 20;
    String query = null;
    UserListRequestDTO userListRequestDTO = new UserListRequestDTO(query, size, page);
    List<User> users = new ArrayList<>();

    Pageable pageable = PageRequest.of(page, size);

    for (int i = 0; i < 20; i++) {
      users.add(new User());
    }

    Page<User> userPage = new PageImpl<>(users, PageRequest.of(page, size), users.size());

    List<UserListResponseDTO> userListResponseDTO = userPage.map(user -> {
      return new UserListResponseDTO(user.getName(), user.getEmail());
    }).getContent();

    Mockito.when(userRepository.findUsersWithFilters(query, pageable)).thenReturn(userPage);

    List<UserListResponseDTO> result = userService.list(userListRequestDTO);

    assertEquals(size, result.size());
    assertEquals(userListResponseDTO, result);
  }

  @Test
  @DisplayName("should user with the username")
  public void getByUsername() {
    String username = "test";
    String email = "test@gmail.com";
    User newUser = new User();
    newUser.setUsername(username);
    newUser.setEmail(email);

    Mockito.when(userRepository.findByUsername(username)).thenReturn(Optional.of(newUser));

    UserDetailResponseDTO result = userService.findByUsername(username);

    assertEquals(result.email(), newUser.getEmail());
  }

  @Test
  @DisplayName("should return a exception if user is not found")
  public void getByUsernameException() {

    Mockito.when(userRepository.findByUsername("test")).thenReturn(Optional.empty());

    RecordNotFoundException thrown = assertThrows(
            RecordNotFoundException.class,
            () -> userService.findByUsername("test")
    );

  }

  @Test
  @DisplayName("should update an user and return it")
  public void updateUser() throws JsonMappingException {
    ObjectMapper objectMapper = new ObjectMapper();
    User user = new User();
    user.setName("test1");
    user.setFederalTaxId("999.999.999-00");
    UpdateUserRequestDTO data = new UpdateUserRequestDTO("test2", null, "123.456.789-00");

    Mockito.when(userRepository.findByUsername("test")).thenReturn(Optional.of(user));

    User updatedUser = objectMapper.updateValue(user, data);

    UserDetailResponseDTO userDetailResponseDTO = new UserDetailResponseDTO(updatedUser.getName(), updatedUser.getEmail());

    UserDetailResponseDTO updatedUserResponse = userService.update(1, data);

    assertEquals(updatedUserResponse, userDetailResponseDTO);
  }
}