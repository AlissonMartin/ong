package com.github.AlissonMartin.ong.models;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "states")
public class State {

    @Id
    @GeneratedValue
    private int id;

    private String name;

    private String slug;

    @OneToMany(mappedBy = "state")
    private Set<Institution> institutions;
}
