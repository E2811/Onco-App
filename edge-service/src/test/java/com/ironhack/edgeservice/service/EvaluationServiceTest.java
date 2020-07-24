package com.ironhack.edgeservice.service;

import com.ironhack.edgeservice.client.EvaluationClient;
import com.ironhack.edgeservice.client.PatientClient;
import com.ironhack.edgeservice.controller.dto.*;
import com.ironhack.edgeservice.enums.*;
import com.ironhack.edgeservice.model.DoctorEvaluation;
import com.ironhack.edgeservice.model.Patient;
import com.ironhack.edgeservice.model.PatientEvaluation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest
class EvaluationServiceTest {
    @Autowired
    private EvaluationService evaluationService;
    @MockBean
    private EvaluationClient evaluationClient;
    @MockBean
    private PatientService patientService;
    private Patient patient;
    private PatientMV patientMV;
    private PatientEvaluation patientEvaluation;
    private DoctorEvaluation doctorEvaluation;
    private DoctorEvaluationDto doctorEvaluationDto;
    private EvaluationMV evaluationMV;

    @BeforeEach
    void setUp() {
        patient = new Patient("Manuel Pérez", "698547148", "manu@gmail.com", Sex.M,82, 180,11, LocalDate.of(1965,5,12), 1);
        patientMV = new PatientMV(3,"Manuel Pérez", "698547148", "manu@gmail.com", Sex.M,82, 180, LocalDate.of(1965,5,12), 1,"manuelPerez");
        when(patientService.findById(3)).thenReturn(patientMV);
        when(patientService.findByUsername("manuelPerez")).thenReturn(patientMV);
        patientEvaluation = new PatientEvaluation(68, Intake.LESSER,Symptoms.MOUTH_SORES,Activity.RESTRICTED ,3);
        doctorEvaluationDto = new DoctorEvaluationDto(Metabolic.MODERATE, Category.B, 1);
        doctorEvaluation = new DoctorEvaluation(Metabolic.MODERATE, Category.B, patientEvaluation);
        when(evaluationClient.create(Mockito.any(PatientEvaluation.class))).thenAnswer(i -> i.getArguments()[0]);
        when(evaluationClient.createDoctorEval(Mockito.any(DoctorEvaluationDto.class))).thenReturn(doctorEvaluation);
        doNothing().when(evaluationClient).delete(1);
        when(evaluationClient.findByPatient(3)).thenReturn(Arrays.asList(patientEvaluation));
        when(evaluationClient.findById(1)).thenReturn(patientEvaluation);
        when(evaluationClient.findByEvaluation(1)).thenReturn(doctorEvaluation);
        doNothing().when(evaluationClient).deleteDoctorEvaluation(1);
        doNothing().when(evaluationClient).update(1);
        evaluationMV = new EvaluationMV(1,true,68,Intake.LESSER, Symptoms.MOUTH_SORES,Activity.RESTRICTED,Metabolic.MODERATE, Category.B, LocalDate.now(),3);
    }

    @Test
    void create() {
        assertEquals(Symptoms.MOUTH_SORES, evaluationService.create(patientEvaluation).getSymptoms());
    }

    @Test
    void createFakeEval() {
        assertThrows(NullPointerException.class, ()-> evaluationService.createFakeEval(patientEvaluation));
    }

    @Test
    void createDoctorEval() {
        assertEquals(Metabolic.MODERATE, evaluationService.createDoctorEval(doctorEvaluationDto).getMetabolic());
    }

    @Test
    void createFakeDoctorEval() {
        assertThrows(NullPointerException.class, ()-> evaluationService.createFakeDoctorEval(doctorEvaluationDto));
    }

    @Test
    void findByPatient() {
        assertEquals(1, evaluationService.findByPatient("manuelPerez").size());
    }

    @Test
    void findByPatientFake() {
        assertThrows(NullPointerException.class, ()-> evaluationService.findByPatientFake("pepito"));
    }

    @Test
    void findById() {
        assertEquals(Symptoms.MOUTH_SORES, evaluationService.findById(1).getSymptoms());
    }

    @Test
    void findByIdFake() {
        assertThrows(NullPointerException.class, ()-> evaluationService.findByIdFake(2));
    }

    @Test
    void findByPatientEval() {
        assertEquals(Metabolic.MODERATE, evaluationService.findByPatientEval(1).getMetabolic());
    }

    @Test
    void findByPatientEvalFake() {
        assertThrows(NullPointerException.class, ()-> evaluationService.findByPatientEvalFake(200));
    }

    @Test
    void deleteDoctorEval() {
        evaluationService.deleteDoctorEval(1);
    }

    @Test
    void deleteDoctorEvalFake() {
        assertThrows(NullPointerException.class, ()-> evaluationService.deleteDoctorEvalFake(1));
    }

    @Test
    void deletePatientEval() {
        evaluationService.deletePatientEval(1);;
    }

    @Test
    void deletePatientEvalFake() {
        assertThrows(NullPointerException.class, ()-> evaluationService.deletePatientEvalFake(1));
    }

    @Test
    void findCompleteEval() {
        assertEquals(0, evaluationService.findCompleteEval("manuelPerez").size());
    }

    @Test
    void findCompleteFake() {
        assertThrows(NullPointerException.class, ()-> evaluationService.findCompleteFake("pepito"));
    }

    @Test
    void update() {
        evaluationService.update(1);
    }

    @Test
    void updateFake() {
        assertThrows(NullPointerException.class, ()-> evaluationService.updateFake(2));
    }
}