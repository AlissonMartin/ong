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

    public List<JobListResponseDTO> list(String search, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        Page<Job> jobs = jobRepository.findJobsWithFilters(search, pageable);

        return jobs.map(job -> {
            return new JobListResponseDTO(job.getId(), job.getName(), job.getDescription());
        }).stream().toList();
    }

    public JobDetailResponseDTO getById(int id) {
        Job job = jobRepository.findById(id).orElseThrow(()-> new RecordNotFoundException("Trabalho não encontrado."));

        return new JobDetailResponseDTO(job.getName(), job.getDescription());
    }

    public List<JobListResponseDTO> listByInstitution(int institutionId) {
        List<Job> jobs = jobRepository.findAllByInstitution_IdAndDeletedAtIsNull(institutionId);
        return jobs.stream()
                .map(job -> new JobListResponseDTO(job.getId(),job.getName(), job.getDescription()))
                .toList();
    }

}
