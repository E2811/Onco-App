package com.ironhack.evaluationclient.service;

import com.ironhack.evaluationclient.controller.dto.DoctorEvaluationDto;
import com.ironhack.evaluationclient.enums.*;
import com.ironhack.evaluationclient.exception.IdNotFoundException;
import com.ironhack.evaluationclient.model.DoctorEvaluation;
import com.ironhack.evaluationclient.model.PatientEvaluation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
class PatientEvaluationServiceTest {

    @Autowired
    private PatientEvaluationService patientEvaluationService;
    @Autowired
    private DoctorEvaluationService doctorEvaluationService;

    @Test
    void create() {
        PatientEvaluation patientEvaluation = new PatientEvaluation(69, Intake.SCANTY, Symptoms.DIARRHOEA, Activity.RESTRICTED,3);
        assertEquals(69.00,patientEvaluationService.create(patientEvaluation).getWeight());
        DoctorEvaluationDto doctorEvaluation = new DoctorEvaluationDto(Metabolic.MINOR, Category.A,patientEvaluation.getId());
        doctorEvaluationService.create(doctorEvaluation);
    }

    @Test
    void findById() {
        assertEquals(55.2, patientEvaluationService.findById(1).getWeight());
    }

    @Test
    void findByPatient() {
        assertEquals(1, patientEvaluationService.findByPatient(1).size());
    }

    @Test
    void findByPatient_notFound() {
        assertThrows(IdNotFoundException.class,()-> patientEvaluationService.findById(9999));
    }

    @Test
    void delete() {
        doctorEvaluationService.delete(3);
    }
}