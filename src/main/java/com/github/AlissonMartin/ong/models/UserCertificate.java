package com.github.AlissonMartin.ong.models;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "user_certificates")
public class UserCertificate {

  @Id
  @GeneratedValue
  private int id;

  @ManyToOne
  @JoinColumn(name = "certificate_id")
  private Certificate certificate;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  @Temporal(TemporalType.DATE)
  private Date acquiredAt;

  @Temporal(TemporalType.DATE)
  private Date expirationDate;
}
