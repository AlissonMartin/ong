package com.github.AlissonMartin.ong.dtos;

import com.github.AlissonMartin.ong.models.Job;
import com.github.AlissonMartin.ong.models.Post;
import java.util.List;

public record InstitutionDetailResponseDTO(
    int id,
    String fullName,
    String name,
    String description,
    String federalTaxId,
    Long cnae,
    String address,
    int number,
    String complement,
    String district,
    int zip,
    String city,
    String state,
    String email,
    String profilePhotoUrl,
    String bannerUrl,
    List<PostSimpleResponseDTO> posts,
    List<JobSimpleResponseDTO> jobs
) {}
