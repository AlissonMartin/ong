package com.github.AlissonMartin.ong.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.AlissonMartin.ong.dtos.*;
import com.github.AlissonMartin.ong.enums.Criteria;
import com.github.AlissonMartin.ong.exceptions.RecordNotFoundException;
import com.github.AlissonMartin.ong.models.Achievement;
import com.github.AlissonMartin.ong.models.User;
import com.github.AlissonMartin.ong.repositories.AchievementRepository;
import com.github.AlissonMartin.ong.repositories.UserRepository;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class UserService {

  @Autowired
  UserRepository userRepository;

  @Autowired
  PasswordEncoder passwordEncoder;

  @Autowired
  S3Service s3Service;

  @Autowired
  EmailService emailService;

  @Autowired
  UserAchievementService userAchievementService;

  @Autowired
  AchievementRepository achievementRepository;

  public User create(RegisterRequestDTO data) throws MessagingException, IOException {
    User newUser = new User();

    newUser.setName(data.name());
    newUser.setEmail(data.email());
    newUser.setPassword(passwordEncoder.encode(data.password()));
    newUser.setFederalTaxId(data.federalTaxId());
    newUser.setRole(data.role());
    newUser.generateVerificationCode();

    emailService.sendConfirmationEmail(newUser.getEmail(), newUser.getVerificationCode());
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
    User user = userRepository.findByUsername(username).orElseThrow(()-> new RecordNotFoundException("Usuário não encontrado"));

    return new UserDetailResponseDTO(user.getName(), user.getUsername(), user.getEmail(), user.getFederalTaxId(), user.getPhotoUrl());
  }

  public UserDetailResponseDTO update(int id, UpdateUserRequestDTO data) {
    User user = userRepository.findById(id).orElseThrow(()-> new RecordNotFoundException("Usuário não encontrado"));
    String photoUrl = "";
    if (data.photo() != null) {
      photoUrl = s3Service.uploadFile(data.photo());
    }

    ObjectMapper objectMapper = new ObjectMapper();

    try {
      if (data.name() != null) {
        user.setName(data.name());
      }
      if (data.password() != null) {
        user.setPassword(data.password());
      }
      if (data.federalTaxId() != null) {
        user.setFederalTaxId(data.federalTaxId());
      }
      if (data.photo() != null) {
        user.setPhotoUrl(photoUrl);
      }
    } catch (Exception e) {
      throw new RuntimeException("Erro ao atualizar o usuário", e);
    }

    if (user.isComplete()) {
      Achievement achievement = achievementRepository.findByCriteria(Criteria.FULL_PROFILE);
      userAchievementService.create(user, achievement);
    }
    userRepository.save(user);

    return new UserDetailResponseDTO(user.getName(), user.getUsername(), user.getEmail(), user.getFederalTaxId(), user.getPhotoUrl());
  }
}
