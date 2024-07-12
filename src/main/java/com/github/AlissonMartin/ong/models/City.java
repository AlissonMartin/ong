package com.github.AlissonMartin.ong.models;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "cities")
public class City {

    @Id
    @GeneratedValue
    private int id;

    private String name;

    private int ibge_id;

    private String slug;

    @ManyToOne
    @JoinColumn(name = "state_id")
    private State state;

    @OneToMany
    private Set<Institution> institutions;
}
