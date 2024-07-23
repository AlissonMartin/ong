package com.github.AlissonMartin.ong.services;

import com.github.AlissonMartin.ong.dtos.JobDetailResponseDTO;
import com.github.AlissonMartin.ong.dtos.JobListRequest;
import com.github.AlissonMartin.ong.dtos.JobListResponseDTO;
import com.github.AlissonMartin.ong.dtos.UserListResponseDTO;
import com.github.AlissonMartin.ong.exceptions.RecordNotFoundException;
import com.github.AlissonMartin.ong.models.Job;
import com.github.AlissonMartin.ong.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {

    @Autowired
    JobRepository jobRepository;

    public List<JobListResponseDTO> list(JobListRequest data) {
        Pageable pageable = PageRequest.of(data.page(), data.size());

        Page<Job> jobs = jobRepository.findJobsWithFilters(data.search(), pageable);

        return jobs.map(job -> {
            return new JobListResponseDTO(job.getName(), job.getDescription());
        }).stream().toList();
    }

    public JobDetailResponseDTO getById(int id) {
        Job job = jobRepository.findById(id).orElseThrow(()-> new RecordNotFoundException("Trabalho não encontrado."));

        return new JobDetailResponseDTO(job.getName(), job.getDescription());
    }

}
