package com.ironhack.edgeservice.service;

import com.ironhack.edgeservice.client.ResultClient;
import com.ironhack.edgeservice.controller.dto.PatientMV;
import com.ironhack.edgeservice.controller.dto.PatientWeight;
import com.ironhack.edgeservice.enums.*;
import com.ironhack.edgeservice.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest
class ResultServiceTest {

    @MockBean
    private ResultClient resultClient;
    @MockBean
    private PatientService patientService;
    @MockBean
    private EvaluationService evaluationService;
    @Autowired
    private ResultService resultService;

    private PatientMV patientMV;
    private DoctorEvaluation doctorEvaluation;
    private PatientEvaluation patientEvaluation;

    @BeforeEach
    void setUp() {
        patientMV = new PatientMV(1,"Manuel Pérez", "698547148", "manu@gmail.com", Sex.M,82, 180, LocalDate.of(1965,5,12), 1,"manuelPerez");
        User user= new User(1, "manuelPerez", "barco", Role.ROLE_PATIENT);
        doctorEvaluation = new DoctorEvaluation(Metabolic.MODERATE, Category.B, patientEvaluation);
        patientEvaluation = new PatientEvaluation(68, Intake.LESSER,Symptoms.MOUTH_SORES,Activity.RESTRICTED ,1);
        patientEvaluation.setId(1);
        Result result = new Result(60,50,2,2000,1);
        when(evaluationService.findById(1)).thenReturn(patientEvaluation);
        when(evaluationService.findByPatientEval(1)).thenReturn(doctorEvaluation);
        when(patientService.findById(1)).thenReturn(patientMV);
        doNothing().when(patientService).update(new PatientWeight(1,68));
        doNothing().when(evaluationService).update(1);
        doNothing().when(resultClient).delete(1);
        when(resultClient.create(Mockito.any(Result.class))).thenAnswer(i -> i.getArguments()[0]);
        when(resultClient.findByEvaluation(1)).thenReturn(result);
    }

    @Test
    void create() {
        assertEquals(1530.77, resultService.create(1).getCaloriesNeeded());
    }

    @Test
    void createFake() {
        assertThrows(NullPointerException.class, ()-> resultService.createFake(1));
    }

    @Test
    void delete() {
        resultService.delete(1);
    }

    @Test
    void deleteFake() {
        assertThrows(NullPointerException.class, ()-> resultService.deleteFake(1));
    }

    @Test
    void findByEvaluation() {
        assertEquals(2000, resultService.findByEvaluation(1).getCaloriesNeeded());
    }

    @Test
    void findByEvaluationFake() {
        assertThrows(NullPointerException.class, ()-> resultService.findByEvaluationFake(1));
    }
}