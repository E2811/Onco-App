package com.ironhack.edgeservice.controller.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.edgeservice.controller.dto.DoctorDto;
import com.ironhack.edgeservice.controller.dto.DoctorMV;
import com.ironhack.edgeservice.enums.Specialty;
import com.ironhack.edgeservice.model.Doctor;
import com.ironhack.edgeservice.service.DoctorService;
import com.ironhack.edgeservice.service.StatisticService;
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

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class DoctorControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    @MockBean
    private DoctorService doctorService;
    @MockBean
    private StatisticService statisticService;

    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private Doctor doctor;
    private DoctorDto doctorDto;
    private DoctorMV doctorMV;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        doctor = new Doctor("Gema Montalvo", 7, Specialty.PEDIATRICIAN, "gema@gmail.com");
        doctor.setId(1);
        doctorDto = new DoctorDto("Gema Montalvo",  Specialty.PEDIATRICIAN, "gema@gmail.com", "gemaMontalvo", "blueWater");
        doctorMV = new DoctorMV(doctor.getId(),"Gema Montalvo", Specialty.PEDIATRICIAN, "gema@gmail.com" , "gemaMontalvo");
        when(doctorService.findById(1)).thenReturn(doctorMV);
        when(doctorService.findAll()).thenReturn(Arrays.asList(doctorMV));
        when(doctorService.create(doctorDto)).thenReturn(doctorMV);
    }

    @Test
    @WithMockUser(username = "admin",roles = "ADMIN")
    void createDoctor() throws Exception {
        this.mockMvc.perform(post("/doctor/save")
                .content(objectMapper.writeValueAsString(doctorDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    @WithMockUser(username = "admin",roles = "ADMIN")
    void findAllDoctors() throws Exception {
        this.mockMvc.perform(get("/doctor/findAll"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin",roles = "ADMIN")
    void findById() throws Exception {
        this.mockMvc.perform(get("/doctor/find_by_id/1"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin",roles = "ADMIN")
    void findStatistics() throws Exception {
        this.mockMvc.perform(get("/doctor/statistics/activity"))
                .andExpect(status().isOk());
    }
}