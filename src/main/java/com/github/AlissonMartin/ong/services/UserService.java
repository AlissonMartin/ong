package com.github.AlissonMartin.ong.services;

import com.github.AlissonMartin.ong.dtos.*;
import com.github.AlissonMartin.ong.exceptions.RecordNotFoundException;
import com.github.AlissonMartin.ong.models.User;
import com.github.AlissonMartin.ong.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

  @Autowired
  UserRepository userRepository;

  @Autowired
  PasswordEncoder passwordEncoder;

  public User create(RegisterRequestDTO data) {
    User newUser = new User();

    newUser.setName(data.name());
    newUser.setEmail(data.email());
    newUser.setPassword(passwordEncoder.encode(data.password()));
    newUser.setFederalTaxId(data.federalTaxId());
    newUser.setRole(data.role());

    return userRepository.save(newUser);
  }

  public List<UserListResponseDTO> list(UserListRequestDTO data) {
    Pageable pageable = PageRequest.of(data.page(), data.size());

    Page<User> userPage = userRepository.findUsersWithFilters(data.search(), pageable);

    return userPage.map(user -> {
        return new UserListResponseDTO(user.getName(), user.getEmail());
    }).stream().toList();
  }

  public UserDetailResponseDTO findByUsername(String username) {
    Optional<User> userOptional = userRepository.findByUsername(username);

    return userOptional.map(user -> new UserDetailResponseDTO(user.getName(), user.getEmail())).orElseThrow(()-> new RecordNotFoundException("Usuário não encontrado"));
  }

  public void update(int id, UpdateUserRequestDTO data) {

  }
}
