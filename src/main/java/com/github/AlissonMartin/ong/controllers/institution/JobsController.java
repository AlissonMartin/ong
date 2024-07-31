package com.github.AlissonMartin.ong.controllers.institution;

import com.github.AlissonMartin.ong.dtos.JobCreateRequestDTO;
import com.github.AlissonMartin.ong.models.Job;
import com.github.AlissonMartin.ong.services.institution.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/institution/jobs")
public class JobsController {

    @Autowired
    JobService jobService;

    @PostMapping
    public ResponseEntity<Job> create(JobCreateRequestDTO body) {
        Job job = jobService.create(body);
        return ResponseEntity.ok(job);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Job> delete(@PathVariable("id") int id) {
        Job job = jobService.delete(id);
        return ResponseEntity.ok(job);
    }
}
