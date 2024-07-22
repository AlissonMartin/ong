package com.github.AlissonMartin.ong.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

public class Job {
    @Id
    @GeneratedValue
    private int id;

    private String name;

    private String description;
}
