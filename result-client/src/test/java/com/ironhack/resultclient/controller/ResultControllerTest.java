package com.ironhack.resultclient.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.ironhack.resultclient.model.Result;
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
class ResultControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private Result result;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    void findByEvaluation() throws Exception {
        this.mockMvc.perform(get("/result/by-evaluation/1"))
                .andExpect(status().isOk());
    }

    @Test
    void create() throws Exception {
        result = new Result(22.84, 1.92, -2.78, 3207.96 ,4);
        this.mockMvc.perform(post("/result")
                .content(objectMapper.writeValueAsString(result))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void deleteResult() throws Exception {
        this.mockMvc.perform(delete("/result/2"))
                .andExpect(status().isNoContent());
    }
}