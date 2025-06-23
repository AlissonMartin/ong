package com.github.AlissonMartin.ong.controllers;

import com.github.AlissonMartin.ong.config.security.CustomUserDetails;
import com.github.AlissonMartin.ong.dtos.LoginRequestDTO;
import com.github.AlissonMartin.ong.dtos.RegisterRequestDTO;
import com.github.AlissonMartin.ong.dtos.RegisterResponseDTO;
import com.github.AlissonMartin.ong.models.User;
import com.github.AlissonMartin.ong.services.TokenService;
import com.github.AlissonMartin.ong.services.UserService;
import jakarta.mail.MessagingException;
import org.antlr.v4.runtime.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/authentication")
public class AuthenticationController {

  AuthenticationManager authenticationManager;
  TokenService tokenService;
  UserService userService;

  AuthenticationController(AuthenticationManager authenticationManager, TokenService tokenService, UserService userService) {
    this.authenticationManager = authenticationManager;
    this.tokenService = tokenService;
    this.userService = userService;
  }

  @PostMapping("/login")
  ResponseEntity<String> login(@RequestBody LoginRequestDTO body) {
    Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(body.email(), body.password())
    );
    CustomUserDetails userAuthenticated = (CustomUserDetails) authentication.getPrincipal();

    String token  = tokenService.generateToken(userAuthenticated.getUser());

    return ResponseEntity.ok(token);
  }

  @PostMapping("/register")
  ResponseEntity<RegisterResponseDTO> register(@RequestBody RegisterRequestDTO body) throws MessagingException, IOException {
    User user = userService.create(body);

    String token = tokenService.generateToken(user);

    return ResponseEntity.ok(new RegisterResponseDTO(user.getName(), user.getEmail(), user.getFederalTaxId(), user.getRole().toString(), token));
  }
}
