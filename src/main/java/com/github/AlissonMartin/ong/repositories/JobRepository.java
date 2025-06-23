package com.github.AlissonMartin.ong.repositories;

import com.github.AlissonMartin.ong.models.Job;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface JobRepository extends JpaRepository<Job, Integer> {

    @Query("SELECT j FROM Job j WHERE j.name LIKE %:search%")
    Page<Job> findJobsWithFilters(@Param("search") String search, Pageable pageable);

    Optional<Job> findByIdAndInstitution_Id(int id, int institutionId);

    List<Job> findAllByInstitution_IdAndDeletedAtIsNull(int institutionId);
}
