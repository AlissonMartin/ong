package com.github.AlissonMartin.ong.controllers.institution;

import com.github.AlissonMartin.ong.dtos.JobCreateRequestDTO;
import com.github.AlissonMartin.ong.models.Job;
import com.github.AlissonMartin.ong.services.institution.InstitutionJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/institution/jobs")
public class InstitutionJobsController {

    @Autowired
    InstitutionJobService institutionJobService;

    @PostMapping
    public ResponseEntity<Job> create(JobCreateRequestDTO body) {
        Job job = institutionJobService.create(body);
        return ResponseEntity.ok(job);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Job> delete(@PathVariable("id") int id) {
        Job job = institutionJobService.delete(id);
        return ResponseEntity.ok(job);
    }
}
