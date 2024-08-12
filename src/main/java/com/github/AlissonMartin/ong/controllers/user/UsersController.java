package com.github.AlissonMartin.ong.controllers.user;

import com.github.AlissonMartin.ong.dtos.UpdateUserRequestDTO;
import com.github.AlissonMartin.ong.models.User;
import com.github.AlissonMartin.ong.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/users")
public class UsersController {

  @Autowired
  UserService userService;

  @PutMapping(value = "/update", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<User> update(@ModelAttribute UpdateUserRequestDTO body) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    User userDetails = (User) authentication.getPrincipal();

    User user = userService.update(userDetails.getId(), body);

    return ResponseEntity.ok(user);
  }

}
