package com.github.AlissonMartin.ong.dtos;

public record InstitutionUpdateRequestDTO(
    String full_name,
    String name,
    String description,
    String federal_tax_id,
    Long cnae,
    String address,
    Integer number,
    String complement,
    String district,
    Integer zip,
    Integer cityId,
    Integer stateId,
    String email,
    String profilePhotoUrl,
    String bannerUrl
) {}
