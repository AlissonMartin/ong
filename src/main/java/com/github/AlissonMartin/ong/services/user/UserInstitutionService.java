package com.github.AlissonMartin.ong.services.user;

import com.github.AlissonMartin.ong.dtos.RegisterInstitutionDTO;
import com.github.AlissonMartin.ong.dtos.RegisterInstitutionFormDTO;
import com.github.AlissonMartin.ong.enums.Role;
import com.github.AlissonMartin.ong.models.Institution;
import com.github.AlissonMartin.ong.models.User;
import com.github.AlissonMartin.ong.repositories.CityRepository;
import com.github.AlissonMartin.ong.repositories.InstitutionRepository;
import com.github.AlissonMartin.ong.repositories.StateRepository;
import com.github.AlissonMartin.ong.repositories.UserRepository;
import com.github.AlissonMartin.ong.services.S3Service;
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

  @Autowired
  S3Service s3Service;

  public Institution create(RegisterInstitutionFormDTO data) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    User user = (User) authentication.getPrincipal();

    Institution institution = new Institution();
    institution.setFull_name(data.getFull_name());
    institution.setName(data.getName());
    institution.setEmail(data.getEmail());
    institution.setFederal_tax_id(data.getFederal_tax_id());
    institution.setAddress(data.getAddress());
    institution.setNumber(data.getNumber());
    institution.setComplement(data.getComplement());
    institution.setDistrict(data.getDistrict());
    institution.setZip(data.getZip());
    institution.setCity(cityRepository.findById(data.getCity_id()).orElse(null));
    institution.setState(stateRepository.findById(data.getState_id()).orElse(null));
    institution.setUser(user);
    // Upload profile photo if present
    if (data.getProfilePhoto() != null && !data.getProfilePhoto().isEmpty()) {
      String url = s3Service.uploadFile(data.getProfilePhoto());
      institution.setProfilePhotoUrl(url);
    }
    // Upload banner if present
    if (data.getBanner() != null && !data.getBanner().isEmpty()) {
      String url = s3Service.uploadFile(data.getBanner());
      institution.setBannerUrl(url);
    }
    institutionRepository.save(institution);
    user.setInstitution(institution);
    user.setRole(Role.INSTITUTION);
    userRepository.save(user);
    return institution;
  }
}
