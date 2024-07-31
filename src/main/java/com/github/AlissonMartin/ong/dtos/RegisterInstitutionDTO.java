package com.github.AlissonMartin.ong.dtos;

public record RegisterInstitutionDTO(String full_name, String name, String federal_tax_id, String address, int number, String complement, String district, int zip, int city_id, int state_id, String email) {
}
