package com.github.AlissonMartin.ong.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.AlissonMartin.ong.dtos.InstitutionListRequestDTO;
import com.github.AlissonMartin.ong.models.Institution;
import com.github.AlissonMartin.ong.services.InstitutionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

@WebMvcTest
class InstitutionsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    private InstitutionService institutionService;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

    }

    @DisplayName("should list the institutions")
    public void list() throws Exception {
        InstitutionListRequestDTO institutionListRequestDTO = new InstitutionListRequestDTO("test", 0, 10);

        List<Institution> institutions = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            institutions.add(new Institution());
        }

        Mockito.when(institutionService.list(institutionListRequestDTO.search(), institutionListRequestDTO.page(), institutionListRequestDTO.size())).thenReturn(institutions);

        mockMvc.perform(MockMvcRequestBuilders.get("/institutions").param("search", institutionListRequestDTO.search()).param("page", "0").param("size", "10")).andExpect(MockMvcResultMatchers.status().isOk());
    }
}