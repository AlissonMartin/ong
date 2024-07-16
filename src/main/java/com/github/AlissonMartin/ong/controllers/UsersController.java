package com.github.AlissonMartin.ong.controllers;

import com.github.AlissonMartin.ong.dtos.UpdateUserRequestDTO;
import com.github.AlissonMartin.ong.dtos.UserDetailResponseDTO;
import com.github.AlissonMartin.ong.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/users")
public class UsersController {

  @Autowired
  UserService userService;

  @PutMapping("/update")
  public ResponseEntity<UserDetailResponseDTO> update(@RequestBody UpdateUserRequestDTO body, Principal principal) {
    userService.update(principal)
    return ResponseEntity.ok().build();
  }

}
