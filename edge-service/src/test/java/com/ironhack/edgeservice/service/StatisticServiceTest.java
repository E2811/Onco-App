package com.ironhack.edgeservice.service;

import com.ironhack.edgeservice.controller.dto.EvaluationMV;
import com.ironhack.edgeservice.controller.dto.PatientMV;
import com.ironhack.edgeservice.enums.*;
import com.ironhack.edgeservice.model.Result;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class StatisticServiceTest {

    @MockBean
    private PatientService patientService;
    @MockBean
    private EvaluationService evaluationService;
    @Autowired
    private StatisticService statisticService;

    private PatientMV patientMV;
    private EvaluationMV evaluationMV;

    @BeforeEach
    void setUp() {
        patientMV = new PatientMV(1,"Manuel Pérez", "698547148", "manu@gmail.com", Sex.M,82, 180, LocalDate.of(1965,5,12), 1,"manuelPerez");
        Result result = new Result(60,50,2,2000,1);
        evaluationMV = new EvaluationMV(1,true,68, Intake.LESSER, Symptoms.MOUTH_SORES, Activity.RESTRICTED, Metabolic.MODERATE, Category.B, LocalDate.now(),3);
        when(patientService.findAll()).thenReturn(Arrays.asList(patientMV));
        when(evaluationService.findCompleteEval("manuelPerez")).thenReturn(Arrays.asList(evaluationMV));
    }

    @Test
    void findPatientsCategory() {
        Map<String, Integer> result = new HashMap<>();
        result.put("Mal-nutrido",1);
        assertEquals(result, statisticService.findStatistics("category"));
    }

    @Test
    void findPatientsSymptoms() {
        Map<String, Integer> result = new HashMap<>();
        result.put("MOUTH_SORES",1);
        assertEquals(result, statisticService.findStatistics("symptoms"));
    }

    @Test
    void findPatientsActivity() {
        Map<String, Integer> result = new HashMap<>();
        result.put("RESTRICTED",1);
        assertEquals(result, statisticService.findStatistics("activity"));
    }
}