package com.github.AlissonMartin.ong.models;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "certificates")
public class Certificate {

  @Id
  @GeneratedValue
  private int Id;

  private String name;

  private String description;

  @Temporal(TemporalType.DATE)
  private Date expirationDate;

  @Temporal(TemporalType.TIMESTAMP)
  private Date createdAt;

  @ManyToOne
  @JoinColumn(name = "institution_id")
  private Institution institution;
}
