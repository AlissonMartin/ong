package com.github.AlissonMartin.ong.dtos;

import org.springframework.web.multipart.MultipartFile;

public record JobCreateRequestDTO(String name, String description, MultipartFile image) {
}
