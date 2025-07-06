package com.github.AlissonMartin.ong.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "user_certificates")
@Getter
@Setter
public class UserCertificate {

  @Id
  @GeneratedValue
  private int id;

  private String certificateName;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  @OneToOne
  @JoinColumn(name = "job_application_id")
  @JsonIgnore
  private JobApplication jobApplication;

  @Temporal(TemporalType.DATE)
  private Date acquiredAt;

  @Temporal(TemporalType.DATE)
  private Date expirationDate;
}
