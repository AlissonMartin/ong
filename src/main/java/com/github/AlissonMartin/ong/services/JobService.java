package com.github.AlissonMartin.ong.services;

import com.github.AlissonMartin.ong.exceptions.RecordNotFoundException;
import com.github.AlissonMartin.ong.models.Job;
import com.github.AlissonMartin.ong.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class JobService {

    @Autowired
    JobRepository jobRepository;

    public Page<Job> list(String search, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        Page<Job> jobs = jobRepository.findJobsWithFilters(search, pageable);

        return jobs;
    }

    public Job getById(int id) {
        return jobRepository.findById(id).orElseThrow(()-> new RecordNotFoundException("Trabalho não encontrado."));
    }

}
