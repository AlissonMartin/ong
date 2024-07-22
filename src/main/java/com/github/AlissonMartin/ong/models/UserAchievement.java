package com.github.AlissonMartin.ong.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "user_achievements")
@Setter
@Getter
public class UserAchievement {

    @Id
    @GeneratedValue
    private int id;

    private BigDecimal progress;

    @ManyToOne
    @JoinColumn(name = "achievement_id")
    private Achievement achievement;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private Date dateAchieved;
}
