package com.github.AlissonMartin.ong.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.web.multipart.MultipartFile;

public record UpdateUserRequestDTO(String name, String password, String federalTaxId, @JsonIgnore MultipartFile photo) {
}
