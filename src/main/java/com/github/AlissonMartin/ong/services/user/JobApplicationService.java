package com.github.AlissonMartin.ong.services.user;

import com.github.AlissonMartin.ong.enums.JobApplicationStatus;
import com.github.AlissonMartin.ong.models.Job;
import com.github.AlissonMartin.ong.models.JobApplication;
import com.github.AlissonMartin.ong.models.User;
import com.github.AlissonMartin.ong.repositories.JobApplicationRepository;
import com.github.AlissonMartin.ong.repositories.JobRepository;
import com.github.AlissonMartin.ong.repositories.UserRepository;
import com.github.AlissonMartin.ong.services.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
public class JobApplicationService {

  @Autowired
  S3Service s3Service;

  @Autowired
  UserRepository userRepository;

  @Autowired
  JobRepository jobRepository;

  @Autowired
  JobApplicationRepository jobApplicationRepository;

  public JobApplication create(int userId, int jobId, MultipartFile curriculum) {
    Optional<User> user = userRepository.findById(userId);
    Optional<Job> job = jobRepository.findById(jobId);

    JobApplication jobApplication = new JobApplication();

    jobApplication.setUser(user.get());
    jobApplication.setJob(job.get());
    jobApplication.setCurriculumUrl(s3Service.uploadFile(curriculum));
    jobApplication.setStatus(JobApplicationStatus.OPEN);

    return jobApplicationRepository.save(jobApplication);
  }
}
