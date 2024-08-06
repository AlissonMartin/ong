package com.github.AlissonMartin.ong.services.user;

import com.github.AlissonMartin.ong.dtos.RegisterInstitutionDTO;
import com.github.AlissonMartin.ong.enums.Role;
import com.github.AlissonMartin.ong.models.Institution;
import com.github.AlissonMartin.ong.models.User;
import com.github.AlissonMartin.ong.repositories.CityRepository;
import com.github.AlissonMartin.ong.repositories.InstitutionRepository;
import com.github.AlissonMartin.ong.repositories.StateRepository;
import com.github.AlissonMartin.ong.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserInstitutionService {

  @Autowired
  InstitutionRepository institutionRepository;

  @Autowired
  UserRepository userRepository;

  @Autowired
  CityRepository cityRepository;

  @Autowired
  StateRepository stateRepository;

  public Institution create(RegisterInstitutionDTO data) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    User user = (User) authentication.getPrincipal();

    Institution institution = new Institution();
    institution.setFull_name(data.full_name());
    institution.setName(data.name());
    institution.setEmail(data.email());
    institution.setFederal_tax_id(data.federal_tax_id());
    institution.setAddress(data.address());
    institution.setNumber(data.number());
    institution.setComplement(data.complement());
    institution.setCity(cityRepository.findById(data.city_id()).get());
    institution.setState(stateRepository.findById(data.state_id()).get());

    institutionRepository.save(institution);

    user.setInstitution(institution);
    user.setRole(Role.INSTITUTION);

    userRepository.save(user);

    return institution;
  }
}
