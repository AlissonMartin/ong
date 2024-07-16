package com.github.AlissonMartin.ong.models;

import com.github.AlissonMartin.ong.enums.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "users")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue
    private int id;

    private String name;

    @Column(unique = true)
    private String username;

    @Column(unique = true)
    private String email;

    private String password;

    private String federalTaxId;

    @Enumerated(EnumType.ORDINAL)
    private Role role;
}
