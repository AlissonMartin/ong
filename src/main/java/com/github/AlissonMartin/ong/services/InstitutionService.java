package com.github.AlissonMartin.ong.services;

import com.github.AlissonMartin.ong.exceptions.RecordNotFoundException;
import com.github.AlissonMartin.ong.models.Institution;
import com.github.AlissonMartin.ong.repositories.InstitutionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class InstitutionService {

  @Autowired
  InstitutionRepository institutionRepository;

  public Page<Institution> list(String search, int page, int size) {
    Pageable pageable = PageRequest.of(page, size);

    return institutionRepository.findInstitutionsWithFilters(search, pageable);
  }

  public Institution getById(int id) {
    return institutionRepository.findById(id).orElseThrow(()-> new RecordNotFoundException("Instituicão não encontrada."));
  }
}
