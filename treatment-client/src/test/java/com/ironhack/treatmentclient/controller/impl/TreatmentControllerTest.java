package com.ironhack.treatmentclient.controller.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.ironhack.treatmentclient.enums.Type;
import com.ironhack.treatmentclient.model.Treatment;
import com.ironhack.treatmentclient.repository.TreatmentRepository;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class TreatmentControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private Treatment treatment;

    @BeforeEach
    void setUp() {
        objectMapper.registerModule(new JavaTimeModule());
        mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
        treatment = new Treatment(Type.INMUNOTHERAPY,50, 1);
    }

    @Test
    void create() throws Exception {
        this.mockMvc.perform(post("/treatment/save")
                .content(objectMapper.writeValueAsString(treatment))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }
    @Test
    void create_badRequest() throws Exception {
        treatment.setDose(-10);
        this.mockMvc.perform(post("/treatment/save")
                .content(objectMapper.writeValueAsString(treatment))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void findByPatient() throws Exception {
        this.mockMvc.perform(get("/treatment/byPatient/1"))
                .andExpect(status().isOk());
    }

    @Test
    void findById() throws Exception {
        this.mockMvc.perform(get("/treatment/1"))
                .andExpect(status().isOk());
    }
}