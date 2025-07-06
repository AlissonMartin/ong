package com.github.AlissonMartin.ong.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.AlissonMartin.ong.enums.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;


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

    @OneToMany(mappedBy = "user")
    private List<UserAchievement> userAchievements;

    @OneToMany(mappedBy = "user")
    private List<JobApplication> jobApplications;

    @OneToOne
    @JoinColumn(name = "institution_id")
    private Institution institution;

    @Transient
    private String verificationCode;

    public boolean isComplete() {
        return id != 0 &&
                StringUtils.isNotBlank(name) &&
                StringUtils.isNotBlank(username) &&
                StringUtils.isNotBlank(email) &&
                StringUtils.isNotBlank(password) &&
                StringUtils.isNotBlank(federalTaxId) &&
                StringUtils.isNotBlank(photoUrl);
    }

    public String generateVerificationCode() {
        if (verificationCode == null) {
            Random random = new Random();
            StringBuilder randomNumber = new StringBuilder();

            for (int i = 0; i < 6; i++) {
                int digit = random.nextInt(10); // Gera um número entre 0 e 9
                randomNumber.append(digit);
            }

            this.setVerificationCode(randomNumber.toString());
            return randomNumber.toString();
        } else {
            return null;
        }
    }
}
