package com.ironhack.edgeservice.controller.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
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
class ResultControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    @MockBean
    private EvaluationService evaluationService;
    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    @WithMockUser(username = "admin",roles = "ADMIN")
    void createPatient() throws Exception {
        this.mockMvc.perform(post("/result/save/1"))
                .andExpect(status().isCreated());
    }

    @Test
    @WithMockUser(username = "admin",roles = "ADMIN")
    void findByEvaluation() throws Exception {
        this.mockMvc.perform(get("/result/find_by_evaluation/1"))
                .andExpect(status().isOk());
    }
}