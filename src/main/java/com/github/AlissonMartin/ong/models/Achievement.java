package com.github.AlissonMartin.ong.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

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

    private List<UserAchievement> userAchievements;
}
