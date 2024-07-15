package com.github.AlissonMartin.ong.services;

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

  public List<Institution> list(String search, int page, int size) {
    Pageable pageable = PageRequest.of(page, size);

    Page<Institution> institutionsPage = institutionRepository.findInstitutionsWithFilters(search, pageable);
    return institutionsPage.getContent().stream().toList();
  }

  public Optional<Institution> getById(int id) {
    return institutionRepository.findById(id);
  }
}
