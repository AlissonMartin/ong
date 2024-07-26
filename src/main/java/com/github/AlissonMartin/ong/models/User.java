package com.github.AlissonMartin.ong.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.AlissonMartin.ong.enums.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.util.List;


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

    private String photoUrl;

    @OneToMany
    private List<UserAchievement> userAchievements;

    public boolean isComplete() {
        return id != 0 &&
                StringUtils.isNotBlank(name) &&
                StringUtils.isNotBlank(username) &&
                StringUtils.isNotBlank(email) &&
                StringUtils.isNotBlank(password) &&
                StringUtils.isNotBlank(federalTaxId) &&
                StringUtils.isNotBlank(photoUrl);
    }
}
