package com.ironhack.evaluationclient.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.ironhack.evaluationclient.enums.*;
import com.ironhack.evaluationclient.model.PatientEvaluation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class PatientEvaluationControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private PatientEvaluation patientEvaluation;

    @BeforeEach
    void setUp() {
        objectMapper.registerModule(new JavaTimeModule());
        mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    void create() throws Exception {
       patientEvaluation = new PatientEvaluation(69, Intake.SCANTY, Symptoms.DIARRHOEA, Activity.RESTRICTED,  3);
        this.mockMvc.perform(post("/patient-evaluation/save")
                .content(objectMapper.writeValueAsString(patientEvaluation))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void findByPatient() throws Exception {
        this.mockMvc.perform(get("/patient-evaluation/byPatient/1"))
                .andExpect(status().isOk());
    }

    @Test
    void findById() throws Exception {
        this.mockMvc.perform(get("/patient-evaluation/1"))
                .andExpect(status().isOk());
    }

    @Test
    void deleteVsg() throws Exception {
        this.mockMvc.perform(delete("/patient-evaluation/3"))
                .andExpect(status().isNoContent());
    }
}