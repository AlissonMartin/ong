package com.github.AlissonMartin.ong.services;

import com.github.AlissonMartin.ong.dtos.RegisterRequestDTO;
import com.github.AlissonMartin.ong.models.User;
import com.github.AlissonMartin.ong.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
}
