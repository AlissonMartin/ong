package com.github.AlissonMartin.ong.controllers;

import com.github.AlissonMartin.ong.models.Institution;
import com.github.AlissonMartin.ong.services.InstitutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public/institutions")
public class InstitutionsController {

  @Autowired
  InstitutionService institutionService;

  @GetMapping("/")
  public ResponseEntity<Page<Institution>> list(@RequestParam(value = "search", required = false) String search, @RequestParam("page") int page, @RequestParam int size) {

    Page<Institution> institutions = institutionService.list(search, page, size);

    return ResponseEntity.ok(institutions);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Institution> getById(@PathVariable("id") int id) {
    Institution institution = institutionService.getById(id);

    return ResponseEntity.ok(institution);
  }
}
