package com.github.AlissonMartin.ong.services;

import com.github.AlissonMartin.ong.dtos.InstitutionDetailResponseDTO;
import com.github.AlissonMartin.ong.dtos.InstitutionListResponseDTO;
import com.github.AlissonMartin.ong.dtos.JobDetailResponseDTO;
import com.github.AlissonMartin.ong.dtos.JobListResponseDTO;
import com.github.AlissonMartin.ong.exceptions.RecordNotFoundException;
import com.github.AlissonMartin.ong.models.Institution;
import com.github.AlissonMartin.ong.repositories.InstitutionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InstitutionService {

  @Autowired
  InstitutionRepository institutionRepository;

  public List<InstitutionListResponseDTO> list(String search, int page, int size) {
    Pageable pageable = PageRequest.of(page, size);

    Page<Institution> institutionsPage = institutionRepository.findInstitutionsWithFilters(search, pageable);

    return institutionsPage.map(institution -> {
      return new InstitutionListResponseDTO(institution.getName(), institution.getDescription());
    }).stream().toList();
  }

  public InstitutionDetailResponseDTO getById(int id) {
    Institution institution =  institutionRepository.findById(id).orElseThrow(()-> new RecordNotFoundException("Instituicão não encontrada."));

    return new InstitutionDetailResponseDTO(institution.getName(), institution.getDescription());
  }
}
