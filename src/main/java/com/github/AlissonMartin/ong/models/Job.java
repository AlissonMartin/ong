package com.github.AlissonMartin.ong.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Job {
    @Id
    @GeneratedValue
    private int id;

    private String name;

    private String description;
}
