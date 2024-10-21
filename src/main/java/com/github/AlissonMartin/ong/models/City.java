package com.github.AlissonMartin.ong.models;

import jakarta.persistence.*;

import java.math.BigInteger;
import java.util.Set;

@Entity
@Table(name = "cities")
public class City {

    @Id
    @GeneratedValue
    private BigInteger id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "state_id")
    private State state;

    @OneToMany(mappedBy = "city")
    private Set<Institution> institutions;
}
