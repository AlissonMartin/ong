package com.github.AlissonMartin.ong.services;

import com.github.AlissonMartin.ong.dtos.InstitutionDetailResponseDTO;
import com.github.AlissonMartin.ong.dtos.InstitutionListResponseDTO;

import com.github.AlissonMartin.ong.exceptions.RecordNotFoundException;
import com.github.AlissonMartin.ong.models.Institution;
import com.github.AlissonMartin.ong.repositories.InstitutionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstitutionService {

  @Autowired
  InstitutionRepository institutionRepository;

  public List<InstitutionListResponseDTO> list(String search, int page, int size) {
    Pageable pageable = PageRequest.of(page, size);

    Page<Institution> institutionsPage = institutionRepository.findInstitutionsWithFilters(search, pageable);

    return institutionsPage.map(institution -> {
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
        institution.getPosts(),
        institution.getJobs()
      );
    }).stream().toList();
  }

  public InstitutionDetailResponseDTO getById(int id) {
    Institution institution =  institutionRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Instituicão não encontrada."));
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
        institution.getPosts(),
        institution.getJobs()
    );
  }
}
