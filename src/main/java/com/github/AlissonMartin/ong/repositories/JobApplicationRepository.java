package com.github.AlissonMartin.ong.repositories;

import com.github.AlissonMartin.ong.models.JobApplication;
import com.github.AlissonMartin.ong.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JobApplicationRepository extends JpaRepository<JobApplication, Integer> {

    Optional<JobApplication> findByIdAndUser(Integer id, User user);

    List<JobApplication> findAllByUser(User user);
}
