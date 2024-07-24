package com.github.AlissonMartin.ong.controllers;

import com.github.AlissonMartin.ong.dtos.InstitutionDetailResponseDTO;
import com.github.AlissonMartin.ong.dtos.InstitutionListRequestDTO;
import com.github.AlissonMartin.ong.dtos.InstitutionListResponseDTO;
import com.github.AlissonMartin.ong.models.Institution;
import com.github.AlissonMartin.ong.services.InstitutionService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/institutions")
public class InstitutionsController {

  @Autowired
  InstitutionService institutionService;

  @GetMapping("/")
  public ResponseEntity<List<InstitutionListResponseDTO>> list(@RequestParam("search") String search, @RequestParam("page") int page, @RequestParam int size) {

    List<InstitutionListResponseDTO> institutions = institutionService.list(search, page, size);

    return ResponseEntity.ok(institutions);
  }

  @GetMapping("/{id}")
  public ResponseEntity<InstitutionDetailResponseDTO> getById(@PathVariable("id") int id) {
    InstitutionDetailResponseDTO institution = institutionService.getById(id);

    return ResponseEntity.ok(institution);
  }
}
