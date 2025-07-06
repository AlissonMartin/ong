package com.github.AlissonMartin.ong.dtos;

import com.github.AlissonMartin.ong.enums.JobApplicationStatus;
import java.time.LocalDateTime;

public record JobApplicationWithUserDTO(
    int id,
    JobApplicationStatus status,
    LocalDateTime createdAt,
    String curriculumUrl,
    UserSimpleDTO user,
    String institutionName,
    JobSimpleResponseDTO job
) {}



