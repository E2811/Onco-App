package com.ironhack.doctorclient.controller.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.doctorclient.controller.dto.DoctorDto;
import com.ironhack.doctorclient.enums.Specialty;
import com.ironhack.doctorclient.model.Doctor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest
class DoctorControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private DoctorDto doctor;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
        doctor = new DoctorDto("Gema",7, Specialty.PEDIATRICIAN,"gema@gmail.com");
    }

    @Test
    void createDoctor() throws Exception {
        this.mockMvc.perform(post("/doctor/save")
                .content(objectMapper.writeValueAsString(doctor))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void createDoctor_badRequest() throws Exception {
        doctor.setEmail("hola");
        this.mockMvc.perform(post("/doctor/save")
                .content(objectMapper.writeValueAsString(doctor))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void findAll() throws Exception {
        this.mockMvc.perform(get("/doctors"))
                .andExpect(status().isOk());
    }

    @Test
    void findById() throws Exception {
        this.mockMvc.perform(get("/doctor/1"))
                .andExpect(status().isOk());
    }
    @Test
    void findById_Fails() throws Exception {
        this.mockMvc.perform(get("/doctor/999"))
                .andExpect(status().isNotFound());
    }
}