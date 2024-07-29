package com.github.AlissonMartin.ong.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "jobs")
@Getter
@Setter
public class Job {
    @Id
    @GeneratedValue
    private int id;

    private String name;

    private String description;

    @ManyToOne
    @JoinColumn(name = "institution_id")
    private Institution institution;
}
