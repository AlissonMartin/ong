package com.github.AlissonMartin.ong.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.AlissonMartin.ong.enums.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Entity
@Table(name = "users")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue
    private int id;

    @JsonProperty("name")
    private String name;

    @Column(unique = true)
    private String username;

    @JsonProperty("email")
    @Column(unique = true)
    private String email;

    @JsonProperty("password")
    private String password;

    @JsonProperty("federal_tax_id")
    private String federalTaxId;

    @JsonProperty("role")
    @Enumerated(EnumType.ORDINAL)
    private Role role;

    @OneToMany
    private List<UserAchievement> userAchievements;
}
