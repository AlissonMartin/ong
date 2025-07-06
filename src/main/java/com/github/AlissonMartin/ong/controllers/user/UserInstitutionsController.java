package com.github.AlissonMartin.ong.controllers.user;

import com.github.AlissonMartin.ong.dtos.InstitutionResponseDTO;
import com.github.AlissonMartin.ong.dtos.RegisterInstitutionDTO;
import com.github.AlissonMartin.ong.dtos.RegisterInstitutionFormDTO;
import com.github.AlissonMartin.ong.models.Institution;
import com.github.AlissonMartin.ong.services.user.UserInstitutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserInstitutionsController {

  @Autowired
  UserInstitutionService userInstitutionService;

  public ResponseEntity<Institution> create(@RequestBody RegisterInstitutionFormDTO body) {
    Institution institution = userInstitutionService.create(body);
    return ResponseEntity.ok(institution);
  }
}
