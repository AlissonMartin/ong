package com.github.AlissonMartin.ong.dtos;

import com.github.AlissonMartin.ong.enums.Role;

public record RegisterRequestDTO(String name, String email, String password, String federalTaxId, Role role) {
}
