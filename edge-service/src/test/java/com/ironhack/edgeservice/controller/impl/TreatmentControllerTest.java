package com.ironhack.edgeservice.controller.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.ironhack.edgeservice.controller.dto.TreatmentDto;
import com.ironhack.edgeservice.enums.Type;
import com.ironhack.edgeservice.model.Doctor;
import com.ironhack.edgeservice.model.Treatment;
import com.ironhack.edgeservice.service.DoctorService;
import com.ironhack.edgeservice.service.TreatmentService;
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

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class TreatmentControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    @MockBean
    private TreatmentService treatmentService;

    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private TreatmentDto treatmentDto;

    @BeforeEach
    void setUp() {
        objectMapper.registerModule(new JavaTimeModule());
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        treatmentDto = new TreatmentDto(Type.INMUNOTHERAPY, LocalDateTime.now(), 1);
    }

    @Test
    @WithMockUser(username = "admin",roles = "ADMIN")
    void createTreatment() throws Exception {
        this.mockMvc.perform(post("/treatment/save")
                .content(objectMapper.writeValueAsString(treatmentDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    @WithMockUser(username = "admin",roles = "ADMIN")
    void findByPatient() throws Exception {
        this.mockMvc.perform(get("/treatment/find_by_patient/1"))
                .andExpect(status().isOk());
    }
}