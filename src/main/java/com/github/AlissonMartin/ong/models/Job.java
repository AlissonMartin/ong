package com.github.AlissonMartin.ong.models;

import com.github.AlissonMartin.ong.enums.JobStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.joda.time.DateTime;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "jobs")
@Getter
@Setter
public class Job {
    @Id
    @GeneratedValue
    private int id;

    private String name;

    @Column(length = 1024)
    private String description;

    @Column(length = 1024)
    private String imageUrl;

    @CreationTimestamp
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date deletedAt;

    @Enumerated(EnumType.STRING)
    private JobStatus status;

    @OneToMany(mappedBy = "job")
    private java.util.List<JobApplication> jobApplications;

    @ManyToOne
    @JoinColumn(name = "institution_id")
    @com.fasterxml.jackson.annotation.JsonIgnore
    private Institution institution;

    @ManyToOne
    @JoinColumn(name = "selected_user_id")
    private User selectedUser;
}
