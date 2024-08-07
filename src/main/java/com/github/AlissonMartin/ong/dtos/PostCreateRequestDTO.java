package com.github.AlissonMartin.ong.dtos;

import org.springframework.web.multipart.MultipartFile;

public record PostCreateRequestDTO(String title, String body, MultipartFile image) {
}
