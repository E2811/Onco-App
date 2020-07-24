package com.ironhack.treatmentclient.service;

import com.ironhack.treatmentclient.enums.Type;
import com.ironhack.treatmentclient.exception.IdNotFoundException;
import com.ironhack.treatmentclient.model.Treatment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class TreatmentServiceTest {

    @Autowired
    private TreatmentService treatmentService;

    @Test
    void save() {
        Treatment treatment =   treatment = new Treatment(Type.INMUNOTHERAPY, LocalDate.now(),2);
        assertEquals(2, treatmentService.save(treatment).getPatient());
    }

    @Test
    void findByPatient() {
        assertEquals(2, treatmentService.findByPatient(1).size());
    }

}