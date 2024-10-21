package com.github.AlissonMartin.ong.models;

import com.github.AlissonMartin.ong.enums.Criteria;
import com.github.AlissonMartin.ong.enums.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "achievements")
@Getter
@Setter
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
