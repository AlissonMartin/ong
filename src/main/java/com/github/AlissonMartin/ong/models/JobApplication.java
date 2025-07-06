package com.github.AlissonMartin.ong.models;

import com.github.AlissonMartin.ong.enums.JobApplicationStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "job_applications")
@Getter
@Setter
public class JobApplication {

  @Id
  @GeneratedValue
  private int id;

  private JobApplicationStatus status;

  @CreationTimestamp
  @Column(updatable = false)
  private LocalDateTime createdAt;

  private String curriculumUrl;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  @ManyToOne
  @JoinColumn(name = "job_id")
  private Job job;

  @OneToOne
  private UserCertificate userCertificate;
}
