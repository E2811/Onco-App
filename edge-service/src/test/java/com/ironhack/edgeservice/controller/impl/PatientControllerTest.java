package com.ironhack.edgeservice.controller.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.ironhack.edgeservice.controller.dto.*;
import com.ironhack.edgeservice.enums.Sex;
import com.ironhack.edgeservice.model.Doctor;
import com.ironhack.edgeservice.model.Patient;
import com.ironhack.edgeservice.service.DoctorService;
import com.ironhack.edgeservice.service.PatientDelete;
import com.ironhack.edgeservice.service.PatientService;
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
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class PatientControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    @MockBean
    private PatientService patientService;
    @MockBean
    private PatientDelete patientDelete;

    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private Patient patient;
    private PatientDto patientDto;
    private PatientMV patientMV;

    @BeforeEach
    void setUp() {
        objectMapper.registerModule(new JavaTimeModule());
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        patient = new Patient("Manuel Pérez", "698547148", "manu@gmail.com", Sex.M,82, 180,11, LocalDate.of(1965,5,12), 4);
        patientDto = new  PatientDto("Manuel Pérez", "698547148", "manu@gmail.com", Sex.M,82, 180, LocalDate.of(1965,5,12), 1,"manuelPerez", "montaña");
        patientMV = new PatientMV(1,"Manuel Pérez", "698547148", "manu@gmail.com", Sex.M,82, 180, LocalDate.of(1965,5,12), 1,"manuelPerez");
        when(patientService.findById(1)).thenReturn(patientMV);
        when(patientService.findAll()).thenReturn(Arrays.asList(patientMV));
        when(patientService.create(patientDto)).thenReturn(patientMV);
        doNothing().when(patientDelete).delete(1);
    }

    @Test
    @WithMockUser(username = "admin",roles = "ADMIN")
    void createPatient() throws Exception {
        this.mockMvc.perform(post("/patient/save")
                .content(objectMapper.writeValueAsString(patientDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    @WithMockUser(username = "admin",roles = "ADMIN")
    void createPatient_badRequest() throws Exception {
        patientDto.setEmail("");
        this.mockMvc.perform(post("/patient/save")
                .content(objectMapper.writeValueAsString(patientDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }


    @Test
    @WithMockUser(username = "admin",roles = "ADMIN")
    void findAllPatients() throws Exception {
        this.mockMvc.perform(get("/patient/findAll"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin",roles = "ADMIN")
    void findById() throws Exception {
        this.mockMvc.perform(get("/patient/find_by_id/1"))
                .andExpect(status().isOk());
    }
    @Test
    @WithMockUser(username = "admin",roles = "ADMIN")
    void findByUsername() throws Exception {
        this.mockMvc.perform(get("/patient/find_by_username/manuelPerez"))
                .andExpect(status().isOk());
    }


    @Test
    @WithMockUser(username = "admin",roles = "ADMIN")
    void update() throws Exception {
        PatientWeight patientWeight= new PatientWeight(1, 60);
        this.mockMvc.perform(put("/patient")
                .content(objectMapper.writeValueAsString(patientWeight))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    @WithMockUser(username = "admin",roles = "ADMIN")
    void deletePatient() throws Exception {
        this.mockMvc.perform(delete("/patient/1"))
                .andExpect(status().isNoContent());
    }
}