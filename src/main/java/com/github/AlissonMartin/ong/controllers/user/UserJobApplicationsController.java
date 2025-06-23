package com.github.AlissonMartin.ong.controllers.user;

import com.github.AlissonMartin.ong.enums.JobApplicationStatus;
import com.github.AlissonMartin.ong.models.JobApplication;
import com.github.AlissonMartin.ong.services.user.JobApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@RestController
@RequestMapping("/user/job-applications")
public class UserJobApplicationsController {
    @Autowired
    private JobApplicationService jobApplicationService;

    @PostMapping
    public ResponseEntity<JobApplication> create(@RequestParam int jobId, @RequestParam MultipartFile curriculum) {
        JobApplication jobApplication = jobApplicationService.create(jobId, curriculum);
        return ResponseEntity.ok(jobApplication);
    }

    @GetMapping
    public ResponseEntity<Iterable<JobApplication>> list() {
        return ResponseEntity.ok(jobApplicationService.list());
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobApplication> getById(@PathVariable int id) {
        Optional<JobApplication> jobApplication = jobApplicationService.getById(id);
        return jobApplication.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<JobApplication> updateStatus(@PathVariable int id, @RequestParam JobApplicationStatus status) {
        JobApplication updated = jobApplicationService.updateStatus(id, status);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        jobApplicationService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
