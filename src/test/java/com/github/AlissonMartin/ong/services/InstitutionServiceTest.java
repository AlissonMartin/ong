package com.github.AlissonMartin.ong.services;

import com.github.AlissonMartin.ong.models.Institution;
import com.github.AlissonMartin.ong.repositories.InstitutionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InstitutionServiceTest {

  @Mock
  InstitutionRepository institutionRepository;

  @InjectMocks
  InstitutionService institutionService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  @DisplayName("should return a list of institutions")
  public void listInstitutions() {
    int page = 1;
    int size = 20;
    String query = null;

    List<Institution> institutions = new ArrayList<>();

    Pageable pageable = PageRequest.of(page, size);

    for (int i = 0; i < 20; i++) {
      institutions.add(new Institution());
    }

    Page<Institution> institutionPage = new PageImpl<>(institutions, PageRequest.of(page, size), institutions.size());

    Mockito.when(institutionRepository.findInstitutionsWithFilters(query, pageable)).thenReturn(institutionPage);

    List<Institution> result = institutionService.list(query, page, size);

    assertEquals(size, result.size());
    assertEquals(institutions, result);
  }
}