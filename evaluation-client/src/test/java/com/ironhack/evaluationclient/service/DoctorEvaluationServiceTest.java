package com.ironhack.evaluationclient.service;

import com.ironhack.evaluationclient.enums.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
class DoctorEvaluationServiceTest {

    @Autowired
    private DoctorEvaluationService doctorEvaluationService;


    @Test
    void findById() {
        assertEquals(Category.A, doctorEvaluationService.findById(1).getCategory());
    }

    @Test
    void findByEvaluation() {
        assertEquals(Category.A, doctorEvaluationService.findByEvaluation(1).getCategory());
    }

}