package com.ironhack.patientclient.controller.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.ironhack.patientclient.controller.dto.PatientWeight;
import com.ironhack.patientclient.enums.Sex;
import com.ironhack.patientclient.model.Patient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class PatientControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private Patient patient;

    @BeforeEach
    void setUp() {
        objectMapper.registerModule(new JavaTimeModule());
        mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    void create() throws Exception {
        patient = new Patient("Manuel Pérez", "698547148", "manu@gmail.com", Sex.M,82, 180,11, LocalDate.of(1965,5,12), 4);
        this.mockMvc.perform(post("/patient/save")
                .content(objectMapper.writeValueAsString(patient))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }
    @Test
    void create_isBadRequest() throws Exception {
        patient = new Patient("Manuel Pérez", "698547148", "manu@gmail.com",Sex.M, 82, 180,11, LocalDate.of(1965,5,12), 4);
        patient.setEmail("hola");
        this.mockMvc.perform(post("/patient/save")
                .content(objectMapper.writeValueAsString(patient))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void findById() throws Exception {
        this.mockMvc.perform(get("/patient/1"))
                .andExpect(status().isOk());
    }
    @Test
    void dfindById_notfoun() throws Exception {
        this.mockMvc.perform(get("/patient/10000"))
                .andExpect(status().isNotFound());
    }

    @Test
    void findByDoctor() throws Exception {
        this.mockMvc.perform(get("/patient/by-doctor/1"))
                .andExpect(status().isOk());
    }

    @Test
    void findAll() throws Exception {
        this.mockMvc.perform(get("/patients"))
                .andExpect(status().isOk());
    }

    @Test
    void deletePatient() throws Exception {
        this.mockMvc.perform(delete("/patient/4"))
                .andExpect(status().isNoContent());
    }

    @Test
    void update() throws Exception {
        PatientWeight patientWeight = new PatientWeight(1, 55);
        this.mockMvc.perform(put("/patient")
                .content(objectMapper.writeValueAsString(patientWeight))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}