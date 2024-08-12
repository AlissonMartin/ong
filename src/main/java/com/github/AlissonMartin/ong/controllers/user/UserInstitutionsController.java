package com.github.AlissonMartin.ong.controllers.user;

import com.github.AlissonMartin.ong.dtos.RegisterInstitutionDTO;
import com.github.AlissonMartin.ong.models.Institution;
import com.github.AlissonMartin.ong.services.user.UserInstitutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/institutions")
public class UserInstitutionsController {

  @Autowired
  UserInstitutionService userInstitutionService;

  @PostMapping("")
  public ResponseEntity<Institution> create(@RequestBody RegisterInstitutionDTO body) {
    Institution institution = userInstitutionService.create(body);
    return ResponseEntity.ok(institution);
  }
}
