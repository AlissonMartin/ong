package com.github.AlissonMartin.ong.services.user;

import com.github.AlissonMartin.ong.enums.Criteria;
import com.github.AlissonMartin.ong.enums.JobApplicationStatus;
import com.github.AlissonMartin.ong.models.Job;
import com.github.AlissonMartin.ong.models.JobApplication;
import com.github.AlissonMartin.ong.models.User;
import com.github.AlissonMartin.ong.models.UserAchievement;
import com.github.AlissonMartin.ong.repositories.*;
import com.github.AlissonMartin.ong.services.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
public class JobApplicationService {

  @Autowired
  S3Service s3Service;

  @Autowired
  AchievementRepository achievementRepository;

  @Autowired
  JobRepository jobRepository;

  @Autowired
  JobApplicationRepository jobApplicationRepository;

  @Autowired
  private UserAchievementRepository userAchievementRepository;

  public JobApplication create(int jobId, MultipartFile curriculum) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    User user = (User) authentication.getPrincipal();
    Optional<Job> job = jobRepository.findById(jobId);

    JobApplication jobApplication = new JobApplication();

    if (user.getJobApplications() == null) {
      UserAchievement userAchievement = new UserAchievement();
      userAchievement.setUser(user);

      userAchievement.setAchievement(achievementRepository.findByCriteria(Criteria.FIRSTAPPLY));

      userAchievementRepository.save(userAchievement);
    }

    jobApplication.setUser(user);
    jobApplication.setJob(job.get());
    jobApplication.setCurriculumUrl(s3Service.uploadFile(curriculum));
    jobApplication.setStatus(JobApplicationStatus.ONGOING);


    return jobApplicationRepository.save(jobApplication);
  }

  public Optional<JobApplication> getById(int id) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    User user = (User) authentication.getPrincipal();
    return jobApplicationRepository.findByIdAndUser(id, user);
  }

  public Iterable<JobApplication> list() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    User user = (User) authentication.getPrincipal();

    return jobApplicationRepository.findAllByUser(user);
  }

  public JobApplication updateStatus(int id, JobApplicationStatus status) {
    JobApplication jobApplication = jobApplicationRepository.findById(id)
      .orElseThrow(() -> new RuntimeException("JobApplication não encontrada"));
    jobApplication.setStatus(status);
    return jobApplicationRepository.save(jobApplication);
  }

  public void delete(int id) {
    jobApplicationRepository.deleteById(id);
  }
}


