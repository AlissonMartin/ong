package com.github.AlissonMartin.ong.controllers.institution;

import com.github.AlissonMartin.ong.dtos.JobCreateRequestDTO;
import com.github.AlissonMartin.ong.dtos.JobDetailWithApplicationsDTO;
import com.github.AlissonMartin.ong.models.Job;
import com.github.AlissonMartin.ong.services.institution.InstitutionJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/institution/jobs")
public class InstitutionJobsController {

    @Autowired
    InstitutionJobService institutionJobService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> create(@ModelAttribute JobCreateRequestDTO body) {
        institutionJobService.create(body);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobDetailWithApplicationsDTO> getById(@PathVariable int id) {
        JobDetailWithApplicationsDTO job = institutionJobService.getDetailWithApplicationsById(id);
        return ResponseEntity.ok(job);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Job> delete(@PathVariable("id") int id) {
        Job job = institutionJobService.delete(id);
        return ResponseEntity.ok(job);
    }

    @PutMapping("/{id}/complete")
    public void complete(@PathVariable int id) {
        institutionJobService.updateStatus(id, "COMPLETED");
    }
}
