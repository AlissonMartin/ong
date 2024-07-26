package com.github.AlissonMartin.ong.models;

import com.github.AlissonMartin.ong.enums.Criteria;
import com.github.AlissonMartin.ong.enums.Role;
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

    @Enumerated(EnumType.ORDINAL)
    private Criteria criteria;

    @OneToMany
    private List<UserAchievement> userAchievements;
}
