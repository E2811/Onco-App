package com.ironhack.edgeservice.service;

import com.ironhack.edgeservice.client.TreatmentClient;
import com.ironhack.edgeservice.controller.dto.PatientMV;
import com.ironhack.edgeservice.controller.dto.TreatmentDto;
import com.ironhack.edgeservice.enums.Sex;
import com.ironhack.edgeservice.enums.Type;
import com.ironhack.edgeservice.model.Patient;
import com.ironhack.edgeservice.model.Treatment;
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
import static org.mockito.Mockito.when;

@SpringBootTest
class TreatmentServiceTest {

    @Autowired
    private TreatmentService treatmentService;
    @MockBean
    private PatientService patientService;
    @MockBean
    private TreatmentClient treatmentClient;
    private TreatmentDto treatmentDto;
    private PatientMV patient;
    private Treatment treatment;

    @BeforeEach
    void setUp() {
        treatmentDto = new TreatmentDto(Type.INMUNOTHERAPY, LocalDateTime.now(), 1);
        treatment = new Treatment(Type.INMUNOTHERAPY, LocalDate.now(), 1);
        patient = new PatientMV(1,"Manuel Pérez", "698547148", "manu@gmail.com", Sex.M,82, 180, LocalDate.of(1965,5,12), 1,"manuelPerez");
        when(patientService.findById(1)).thenReturn(patient);
        when(treatmentClient.create(Mockito.any(Treatment.class))).thenAnswer(i -> i.getArguments()[0]);
        when(treatmentClient.findByPatient(1)).thenReturn(Arrays.asList(treatment));
    }

    @Test
    void create() {
        assertEquals(Type.INMUNOTHERAPY, treatmentClient.create(treatment).getType());
    }

    @Test
    void createFakeTreatment() {
        assertThrows(NullPointerException.class, ()-> treatmentService.createFakeTreatment(treatmentDto));
    }

    @Test
    void findByPatient() {
        assertEquals(1, treatmentService.findByPatient(1).size());
    }

    @Test
    void findByPatientFake() {
        assertThrows(NullPointerException.class, ()-> treatmentService.findByPatientFake(1));
    }
}