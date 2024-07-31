package com.github.AlissonMartin.ong.services.institution;

import com.github.AlissonMartin.ong.dtos.JobCreateRequestDTO;
import com.github.AlissonMartin.ong.models.Job;
import com.github.AlissonMartin.ong.models.User;
import com.github.AlissonMartin.ong.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
public class JobService {

  @Autowired
  JobRepository jobRepository;

  public Job create(JobCreateRequestDTO data) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    User user = (User) authentication.getPrincipal();

    Job job = new Job();

    job.setName(data.name());
    job.setDescription(data.description());
    job.setPostedAt(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
    job.setInstitution(user.getInstitution());

    return jobRepository.save(job);
  }
}
