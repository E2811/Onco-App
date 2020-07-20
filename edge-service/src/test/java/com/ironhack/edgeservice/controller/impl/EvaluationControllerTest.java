package com.ironhack.edgeservice.controller.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.ironhack.edgeservice.controller.dto.DoctorEvaluationDto;
import com.ironhack.edgeservice.enums.*;
import com.ironhack.edgeservice.model.PatientEvaluation;
import com.ironhack.edgeservice.service.EvaluationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class EvaluationControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    @MockBean
    private EvaluationService evaluationService;

    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private DoctorEvaluationDto doctorEvaluationDto;
    private PatientEvaluation patientEvaluation;

    @BeforeEach
    void setUp() {
        objectMapper.registerModule(new JavaTimeModule());
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        patientEvaluation = new PatientEvaluation(60, Intake.INCREASED, Symptoms.DIARRHOEA,Activity.AMBULATORY, 1 );
        doctorEvaluationDto = new DoctorEvaluationDto(Metabolic.MODERATE, Category.B, 1);
    }

    @Test
    @WithMockUser(username = "admin",roles = "ADMIN")
    void createPatientEvaluation() throws Exception {
        this.mockMvc.perform(post("/evaluation/save")
                .content(objectMapper.writeValueAsString(patientEvaluation))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    @WithMockUser(username = "admin",roles = "ADMIN")
    void createDoctorEvaluation() throws Exception {
        this.mockMvc.perform(post("/evaluation/complete")
                .content(objectMapper.writeValueAsString(doctorEvaluationDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    @WithMockUser(username = "admin",roles = "ADMIN")
    void findByPatientCompleted() throws Exception {
        this.mockMvc.perform(get("/evaluation/find_by_patient/completed/1"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin",roles = "ADMIN")
    void findByPatient() throws Exception {
        this.mockMvc.perform(get("/evaluation/find_by_patient/1"))
                .andExpect(status().isOk());
    }
}