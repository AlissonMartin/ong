package com.github.AlissonMartin.ong.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "institutions")
@Getter
@Setter
public class Institution {

    @Id
    @GeneratedValue
    private int id;

    private String full_name;

    private String name;

    private String description;

    @Column(unique = true)
    private String federal_tax_id;

    private Long cnae;

    private String address;

    private int number;

    private String complement;

    private String district;

    private int zip;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    @ManyToOne
    @JoinColumn(name = "state_id")
    private State state;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private int area_code;

    private int phone_number;

    private String latitud;

    private String longitud;

    private String lat_lon_error;

    private Date opening_date;

    private String email;

    @OneToMany(mappedBy = "institution")
    @com.fasterxml.jackson.annotation.JsonIgnore
    List<Job> jobs;

    @OneToMany(mappedBy = "institution")
    @com.fasterxml.jackson.annotation.JsonIgnore
    List<Post> posts;

}
