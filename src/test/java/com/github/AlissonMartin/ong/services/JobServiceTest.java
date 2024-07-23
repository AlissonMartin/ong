package com.github.AlissonMartin.ong.services;

import com.github.AlissonMartin.ong.dtos.JobDetailResponseDTO;
import com.github.AlissonMartin.ong.dtos.JobListRequest;
import com.github.AlissonMartin.ong.dtos.JobListResponseDTO;
import com.github.AlissonMartin.ong.models.Job;
import com.github.AlissonMartin.ong.repositories.JobRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class JobServiceTest {

    @Mock
    JobRepository jobRepository;

    @InjectMocks
    JobService jobService;

    @BeforeEach
    void setUp() {
    }

    @Test
    @DisplayName("should return a list jobs")
    void list() {
        List<Job> jobs = new ArrayList<>();
        JobListRequest jobListRequest = new JobListRequest("", 15, 0);
        Pageable pageable = PageRequest.of(jobListRequest.size(), jobListRequest.page());

        for (int i = 0; i < 20; i++) {
            jobs.add(new Job());
        }

        Page<Job> jobPage = new PageImpl<>(jobs, pageable, 20);

        Mockito.when(jobRepository.findJobsWithFilters(jobListRequest.search(), pageable)).thenReturn(jobPage);

        List<JobListResponseDTO> result = jobService.list(jobListRequest);

        assertEquals(result.size(), jobListRequest.size());
    }

    @Test
    @DisplayName("should return a job")
    void getById() {

        Job job = new Job();
        job.setId(1);
        job.setName("test");

        Mockito.when(jobRepository.findById(1)).thenReturn(Optional.of(job));

        JobDetailResponseDTO result = jobService.getById(job.getId());

        assertEquals(result.name(), job.getName());

    }
}