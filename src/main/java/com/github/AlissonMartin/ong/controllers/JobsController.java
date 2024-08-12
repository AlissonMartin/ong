package com.github.AlissonMartin.ong.controllers;

import com.github.AlissonMartin.ong.models.Job;
import com.github.AlissonMartin.ong.services.JobService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/public/jobs")
public class JobsController {

  @Autowired
  JobService jobService;

  @GetMapping
  public ResponseEntity<Page<Job>> list(@RequestParam("search") String search, @RequestParam("page") int page, @RequestParam int size) {

    Page<Job> jobs = jobService.list(search, page, size);

    return ResponseEntity.ok(jobs);
  }
  @Operation(summary = "Private endpoint", security = @SecurityRequirement(name = "bearerAuth"))
  @GetMapping("/{id}")
  public ResponseEntity<Job> getById(@PathVariable("id") int id) {
    Job job = jobService.getById(id);

    return ResponseEntity.ok(job);
  }
}
