package com.github.AlissonMartin.ong.dtos;

import com.github.AlissonMartin.ong.models.Achievement;

import java.util.List;

public record UserDetailResponseDTO(String name, String username, String email, String federalTaxId, String photoUrl) {
}
