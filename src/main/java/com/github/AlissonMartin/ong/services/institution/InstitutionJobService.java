package com.github.AlissonMartin.ong.services.institution;

import com.github.AlissonMartin.ong.dtos.*;
import com.github.AlissonMartin.ong.enums.JobStatus;
import com.github.AlissonMartin.ong.exceptions.RecordNotFoundException;
import com.github.AlissonMartin.ong.models.Job;
import com.github.AlissonMartin.ong.models.User;
import com.github.AlissonMartin.ong.repositories.JobRepository;
import com.github.AlissonMartin.ong.services.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
public class InstitutionJobService {

  @Autowired
  JobRepository jobRepository;

  @Autowired
  S3Service s3Service;

  public Job create(JobCreateRequestDTO data) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    User user = (User) authentication.getPrincipal();

    Job job = new Job();
    job.setName(data.name());
    job.setDescription(data.description());
    job.setCreatedAt(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
    job.setInstitution(user.getInstitution());

    if (data.image() != null && !data.image().isEmpty()) {
      String imageUrl = s3Service.uploadFile(data.image());
      job.setImageUrl(imageUrl);
    }

    return jobRepository.save(job);
  }

  public Job delete(int id) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    User user = (User) authentication.getPrincipal();

    Job job = jobRepository.findByIdAndInstitution_Id(id, user.getInstitution().getId()).orElseThrow(()-> new RecordNotFoundException("Trabalho não encontrado."));

    job.setDeletedAt(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));

    jobRepository.save(job);
    return job;
  }

  public Job update(int id, JobCreateRequestDTO data) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    User user = (User) authentication.getPrincipal();

    Job job = jobRepository.findByIdAndInstitution_Id(id, user.getInstitution().getId()).orElseThrow(()-> new RecordNotFoundException("Trabalho não encontrado."));

    try {
      if (data.name() != null) {
        job.setName(data.name());
      }
      if (data.description() != null) {
        job.setDescription(data.description());
      }
      if (data.image() != null && !data.image().isEmpty()) {
        String imageUrl = s3Service.uploadFile(data.image());
        job.setImageUrl(imageUrl);
      }
    } catch (Exception e) {
      throw new RuntimeException("Erro ao atualizar o usuário", e);
    }

    return jobRepository.save(job);
  }

  public Job getById(int id) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    User user = (User) authentication.getPrincipal();

    return jobRepository.findByIdAndInstitution_Id(id, user.getInstitution().getId())
            .orElseThrow(() -> new RecordNotFoundException("Trabalho não encontrado."));
  }

  public List<Job> list() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    User user = (User) authentication.getPrincipal();

    return jobRepository.findAllByInstitution_IdAndDeletedAtIsNull(user.getInstitution().getId());
  }

  public JobDetailWithApplicationsDTO getDetailWithApplicationsById(int id) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    User user = (User) authentication.getPrincipal();
    Job job = jobRepository.findByIdAndInstitution_Id(id, user.getInstitution().getId())
            .orElseThrow(() -> new RecordNotFoundException("Trabalho não encontrado."));

    List<JobApplicationWithUserDTO> applications = job.getJobApplications() != null ? job.getJobApplications().stream().map(app ->
      new JobApplicationWithUserDTO(
        app.getId(),
        app.getStatus(),
        app.getCreatedAt(),
        app.getCurriculumUrl(),
        app.getUser() != null ? new UserSimpleDTO(
          app.getUser().getId(),
          app.getUser().getName(),
          app.getUser().getEmail(),
          app.getUser().getPhotoUrl()
        ) : null,
        job.getInstitution() != null ? job.getInstitution().getName() : null,
        new JobSimpleResponseDTO(
          job.getId(),
          job.getName(),
          job.getDescription(),
          job.getCreatedAt(),
          job.getDeletedAt()
        )
      )
    ).toList() : List.of();

    return new JobDetailWithApplicationsDTO(
      job.getId(),
      job.getName(),
      job.getDescription(),
      job.getImageUrl(),
      job.getCreatedAt() != null ? job.getCreatedAt().toString() : null,
      job.getDeletedAt() != null ? job.getDeletedAt().toString() : null,
      applications
    );
  }

  public void updateStatus(int id, String status) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    User user = (User) authentication.getPrincipal();
    Job job = jobRepository.findByIdAndInstitution_Id(id, user.getInstitution().getId())
            .orElseThrow(() -> new RecordNotFoundException("Trabalho não encontrado."));
    job.setStatus(JobStatus.valueOf(status));
    jobRepository.save(job);
  }

}
