package com.github.AlissonMartin.ong.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.AlissonMartin.ong.dtos.InstitutionDetailResponseDTO;
import com.github.AlissonMartin.ong.dtos.InstitutionListRequestDTO;
import com.github.AlissonMartin.ong.dtos.InstitutionListResponseDTO;
import com.github.AlissonMartin.ong.dtos.JobSimpleResponseDTO;
import com.github.AlissonMartin.ong.dtos.PostSimpleResponseDTO;
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

        List<InstitutionListResponseDTO> institutionListResponseDTOS = new ArrayList<>();
        List<PostSimpleResponseDTO> posts = new ArrayList<>();
        List<JobSimpleResponseDTO> jobs = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            institutionListResponseDTOS.add(
                new InstitutionListResponseDTO(
                    i, // id
                    "Full Name " + i,
                    "Name " + i,
                    "Description " + i,
                    "FederalTaxId" + i,
                    123456L + i,
                    "Address " + i,
                    100 + i,
                    "Complement " + i,
                    "District " + i,
                    10000 + i,
                    "City " + i,
                    "State " + i,
                    "email" + i + "@test.com",
                    null, // profilePhotoUrl
                    null, // bannerUrl
                    posts,
                    jobs
                )
            );
        }

        Mockito.when(institutionService.list(institutionListRequestDTO.search(), institutionListRequestDTO.page(), institutionListRequestDTO.size())).thenReturn(institutionListResponseDTOS);

        mockMvc.perform(MockMvcRequestBuilders.get("/institutions")
                .param("search", institutionListRequestDTO.search())
                .param("page", "0")
                .param("size", "10"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.length()", org.hamcrest.Matchers.is(20)));
    }

    @DisplayName("should return one institution info")
    public void list2() throws Exception {
        List<PostSimpleResponseDTO> posts = new ArrayList<>();
        List<JobSimpleResponseDTO> jobs = new ArrayList<>();
        InstitutionDetailResponseDTO responseDTO = new InstitutionDetailResponseDTO(
            1,
            "Full Name Test",
            "test",
            "Description Test",
            "FederalTaxIdTest",
            123456L,
            "Address Test",
            100,
            "Complement Test",
            "District Test",
            10000,
            "City Test",
            "State Test",
            "email@test.com",
            null, // profilePhotoUrl
            null, // bannerUrl
            posts,
            jobs
        );
        Mockito.when(institutionService.getById(1)).thenReturn(responseDTO);

        mockMvc.perform(MockMvcRequestBuilders.get("/institutions/1"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("test"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
            .andExpect(MockMvcResultMatchers.jsonPath("$.fullName").value("Full Name Test"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.posts").isArray())
            .andExpect(MockMvcResultMatchers.jsonPath("$.jobs").isArray());
    }
}