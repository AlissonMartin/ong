package com.github.AlissonMartin.ong.repositories;

import com.github.AlissonMartin.ong.models.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobApplicationRepository extends JpaRepository<JobApplication, Integer> {
}
