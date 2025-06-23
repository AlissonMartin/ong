package com.github.AlissonMartin.ong.dtos;

import java.util.Date;

public record PostResponseDTO(
    int id,
    String title,
    String body,
    String imageUrl,
    Date createdAt,
    Date updatedAt,
    Integer institutionId
) {}

