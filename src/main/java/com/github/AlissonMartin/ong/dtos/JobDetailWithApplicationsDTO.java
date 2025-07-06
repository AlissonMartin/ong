package com.github.AlissonMartin.ong.dtos;

import com.github.AlissonMartin.ong.models.JobApplication;
import java.util.List;

public record JobDetailWithApplicationsDTO(
    int id,
    String name,
    String description,
    String imageUrl,
    String createdAt,
    String deletedAt,
    List<JobApplicationWithUserDTO> jobApplications
) {}

