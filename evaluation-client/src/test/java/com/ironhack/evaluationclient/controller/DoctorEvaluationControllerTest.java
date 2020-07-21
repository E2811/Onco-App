package com.ironhack.evaluationclient.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.ironhack.evaluationclient.controller.dto.DoctorEvaluationDto;
import com.ironhack.evaluationclient.enums.*;
import com.ironhack.evaluationclient.model.DoctorEvaluation;
import com.ironhack.evaluationclient.model.PatientEvaluation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class DoctorEvaluationControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private DoctorEvaluationDto doctorEvaluation;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    void create() throws Exception {
        doctorEvaluation = new DoctorEvaluationDto(Metabolic.MINOR, Category.A,2);
        this.mockMvc.perform(post("/doctor-evaluation/save")
                .content(objectMapper.writeValueAsString(doctorEvaluation))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void findByEvaluation() throws Exception {
        this.mockMvc.perform(get("/doctor-evaluation/associated/1"))
                .andExpect(status().isOk());
    }

    @Test
    void findById() throws Exception {
        this.mockMvc.perform(get("/doctor-evaluation/1"))
                .andExpect(status().isOk());
    }

    @Test
    void findById_notFound() throws Exception {
        this.mockMvc.perform(get("/doctor-evaluation/9999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteEvaluation() throws Exception {
        this.mockMvc.perform(delete("/doctor-evaluation/4"))
                .andExpect(status().isNoContent());
    }
}