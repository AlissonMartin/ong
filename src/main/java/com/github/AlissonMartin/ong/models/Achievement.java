package com.github.AlissonMartin.ong.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "achievements")
public class Achievement {

    @Id
    @GeneratedValue
    private int id;

    private String name;

    private String description;

    private String criteria;

    @OneToMany
    private List<UserAchievement> userAchievements;
}
