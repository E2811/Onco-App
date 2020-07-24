package com.ironhack.edgeservice.service;

import com.ironhack.edgeservice.controller.dto.PatientMV;
import com.ironhack.edgeservice.enums.*;
import com.ironhack.edgeservice.model.DoctorEvaluation;
import com.ironhack.edgeservice.model.PatientEvaluation;
import com.ironhack.edgeservice.model.Result;
import com.ironhack.edgeservice.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest
class PatientDeleteTest {

    @MockBean
    private PatientService patientService;
    @MockBean
    private EvaluationService evaluationService;
    @MockBean
    private ResultService resultService;
    @MockBean
    private UserService userService;
    @Autowired
    private PatientDelete patientDelete;
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
        when(patientService.findById(1)).thenReturn(patientMV);
        when(userService.findUserByUsername("manuelPerez")).thenReturn(user);
        when(evaluationService.findByPatient("manuelPerez")).thenReturn(Arrays.asList(patientEvaluation));
        when(evaluationService.findByPatientEval(1)).thenReturn(doctorEvaluation);
        when(resultService.findByEvaluation(1)).thenReturn(result);
        doNothing().when(evaluationService).deleteDoctorEval(1);
        doNothing().when(evaluationService).deletePatientEval(1);
        doNothing().when(userService).delete(1);
        doNothing().when(patientService).delete(1);
    }

    @Test
    void delete() {
        patientDelete.delete(1);
    }
}