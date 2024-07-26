package com.github.AlissonMartin.ong.dtos;

import org.springframework.web.multipart.MultipartFile;

public record UpdateUserRequestDTO(String name, String password, String federal_tax_id, MultipartFile photo) {
}
