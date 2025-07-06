package com.github.AlissonMartin.ong.services;

import com.github.AlissonMartin.ong.dtos.InstitutionUpdateRequestDTO;
import com.github.AlissonMartin.ong.dtos.InstitutionDetailResponseDTO;
import com.github.AlissonMartin.ong.dtos.InstitutionListResponseDTO;
import com.github.AlissonMartin.ong.dtos.PostSimpleResponseDTO;
import com.github.AlissonMartin.ong.dtos.JobSimpleResponseDTO;
import com.github.AlissonMartin.ong.dtos.InstitutionUpdateFormDTO;

import com.github.AlissonMartin.ong.exceptions.RecordNotFoundException;
import com.github.AlissonMartin.ong.models.*;
import com.github.AlissonMartin.ong.repositories.InstitutionRepository;
import com.github.AlissonMartin.ong.repositories.CityRepository;
import com.github.AlissonMartin.ong.repositories.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InstitutionService {

  @Autowired
  InstitutionRepository institutionRepository;
  @Autowired
  CityRepository cityRepository;
  @Autowired
  StateRepository stateRepository;
  @Autowired
  S3Service s3Service;

  public List<InstitutionListResponseDTO> list(String search, int page, int size) {
    Pageable pageable = PageRequest.of(page, size);

    Page<Institution> institutionsPage = institutionRepository.findInstitutionsWithFilters(search, pageable);

    return institutionsPage.map(institution -> {
      var postDTOs = institution.getPosts() != null ? institution.getPosts().stream().map(post ->
          new PostSimpleResponseDTO(
              post.getId(),
              post.getTitle(),
              post.getBody(),
              post.getImageUrl(),
              post.getCreatedAt(),
              post.getDeletedAt()
          )
      ).collect(Collectors.toList()) : null;
      var jobDTOs = institution.getJobs() != null ? institution.getJobs().stream().map(job ->
          new JobSimpleResponseDTO(
              job.getId(),
              job.getName(),
              job.getDescription(),
              job.getCreatedAt(),
              job.getDeletedAt()
          )
      ).collect(Collectors.toList()) : null;
      return new InstitutionListResponseDTO(
        institution.getId(),
        institution.getFull_name(),
        institution.getName(),
        institution.getDescription(),
        institution.getFederal_tax_id(),
        institution.getCnae(),
        institution.getAddress(),
        institution.getNumber(),
        institution.getComplement(),
        institution.getDistrict(),
        institution.getZip(),
        institution.getCity() != null ? institution.getCity().getName() : null,
        institution.getState() != null ? institution.getState().getName() : null,
        institution.getEmail(),
        institution.getProfilePhotoUrl(),
        institution.getBannerUrl(),
        postDTOs,
        jobDTOs
      );
    }).stream().toList();
  }

  public InstitutionDetailResponseDTO getById(int id) {
    Institution institution =  institutionRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Instituicão não encontrada."));
    var postDTOs = institution.getPosts() != null ? institution.getPosts().stream().map(post ->
        new PostSimpleResponseDTO(
            post.getId(),
            post.getTitle(),
            post.getBody(),
            post.getImageUrl(),
            post.getCreatedAt(),
            post.getDeletedAt()
        )
    ).collect(Collectors.toList()) : null;
    var jobDTOs = institution.getJobs() != null ? institution.getJobs().stream().map(job ->
        new JobSimpleResponseDTO(
            job.getId(),
            job.getName(),
            job.getDescription(),
            job.getCreatedAt(),
            job.getDeletedAt()
        )
    ).collect(Collectors.toList()) : null;
    return new InstitutionDetailResponseDTO(
        institution.getId(),
        institution.getFull_name(),
        institution.getName(),
        institution.getDescription(),
        institution.getFederal_tax_id(),
        institution.getCnae(),
        institution.getAddress(),
        institution.getNumber(),
        institution.getComplement(),
        institution.getDistrict(),
        institution.getZip(),
        institution.getCity() != null ? institution.getCity().getName() : null,
        institution.getState() != null ? institution.getState().getName() : null,
        institution.getEmail(),
        institution.getProfilePhotoUrl(),
        institution.getBannerUrl(),
        postDTOs,
        jobDTOs
    );
  }

  public InstitutionDetailResponseDTO update(InstitutionUpdateFormDTO dto) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    User user = (User) authentication.getPrincipal();

    Institution institution = user.getInstitution();

    if (dto.getFull_name() != null) institution.setFull_name(dto.getFull_name());
    if (dto.getName() != null) institution.setName(dto.getName());
    if (dto.getDescription() != null) institution.setDescription(dto.getDescription());
    if (dto.getFederal_tax_id() != null) institution.setFederal_tax_id(dto.getFederal_tax_id());
    if (dto.getCnae() != null) institution.setCnae(dto.getCnae());
    if (dto.getAddress() != null) institution.setAddress(dto.getAddress());
    if (dto.getNumber() != null) institution.setNumber(dto.getNumber());
    if (dto.getComplement() != null) institution.setComplement(dto.getComplement());
    if (dto.getDistrict() != null) institution.setDistrict(dto.getDistrict());
    if (dto.getZip() != null) institution.setZip(dto.getZip());
    if (dto.getCityId() != null) {
      City city = cityRepository.findById(dto.getCityId()).orElse(null);
      institution.setCity(city);
    }
    if (dto.getStateId() != null) {
      State state = stateRepository.findById(dto.getStateId()).orElse(null);
      institution.setState(state);
    }
    if (dto.getEmail() != null) institution.setEmail(dto.getEmail());
    if (dto.getProfilePhoto() != null && !dto.getProfilePhoto().isEmpty()) {
      String url = s3Service.uploadFile(dto.getProfilePhoto());
      institution.setProfilePhotoUrl(url);
    } else if (dto.getProfilePhotoUrl() != null) {
      institution.setProfilePhotoUrl(dto.getProfilePhotoUrl());
    }
    if (dto.getBanner() != null && !dto.getBanner().isEmpty()) {
      String url = s3Service.uploadFile(dto.getBanner());
      institution.setBannerUrl(url);
    } else if (dto.getBannerUrl() != null) {
      institution.setBannerUrl(dto.getBannerUrl());
    }
    institutionRepository.save(institution);
    return getById(institution.getId());
  }
}
