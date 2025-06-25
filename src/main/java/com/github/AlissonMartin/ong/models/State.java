package com.github.AlissonMartin.ong.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "states")
@Getter
@Setter
public class State {

    @Id
    @GeneratedValue
    private int id;

    private String name;

    private String slug;

    @OneToMany(mappedBy = "state")
    private Set<Institution> institutions;
}
