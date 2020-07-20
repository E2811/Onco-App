package com.ironhack.resultclient.service;

import com.ironhack.resultclient.model.Result;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
class ResultServiceTest {
    @Autowired
    private ResultService resultService;

    @Test
    void findByEvaluation() {
        assertEquals(18.73, resultService.findByEvaluation(1).getIMC());
    }

    @Test
    void delete() {
        resultService.delete(4);
    }

    @Test
    void create() {
        Result result = new Result(22.84, 1.92, -2.78, 3207.96 ,4);
        assertEquals(22.84, resultService.create(result).getIMC());
    }
}