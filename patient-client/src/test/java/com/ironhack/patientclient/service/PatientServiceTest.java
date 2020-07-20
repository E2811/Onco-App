package com.ironhack.patientclient.service;

import com.ironhack.patientclient.controller.dto.PatientWeight;
import com.ironhack.patientclient.enums.Sex;
import com.ironhack.patientclient.model.Patient;
import com.netflix.discovery.converters.Auto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
class PatientServiceTest {
    @Autowired
    private PatientService patientService;

    @Test
    void findAll() {
        assertEquals(5, patientService.findAll().size());
    }


    @Test
    void findByDoctor() {
        assertEquals(2, patientService.findByDoctor(2).size());
    }

    @Test
    void findById() {
        assertEquals("Isabel Romero", patientService.findById(1).getName());
    }

    @Test
    void create() {
        Patient patient = new Patient("Manuel Pérez", "698547148", "manu@gmail.com", Sex.M, 82, 180,11, LocalDate.of(1965,5,12), 4);
        assertEquals("Manuel Pérez", patientService.create(patient).getName());
    }

    @Test
    void delete() {
        patientService.delete(5);
    }

    @Test
    void update(){
        PatientWeight patientWeight = new PatientWeight(1, 55);
        patientService.update(patientWeight);
    }
}