package com.github.AlissonMartin.ong.controllers.user;

import com.github.AlissonMartin.ong.models.JobApplication;
import com.github.AlissonMartin.ong.models.User;
import com.github.AlissonMartin.ong.services.user.JobApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/user/jobs")
public class UserJobsController {

  @Autowired
  JobApplicationService jobApplicationService;

  @PostMapping(value = "/{id}/job_application", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<JobApplication> createJobApplication(@PathVariable int id, MultipartFile curriculum) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    User userDetails = (User) authentication.getPrincipal();

    JobApplication jobApplication = jobApplicationService.create(userDetails.getId(), id, curriculum);

    return ResponseEntity.ok(jobApplication);
  }
}
