package com.ironhack.treatmentclient.service;

import com.ironhack.treatmentclient.enums.Type;
import com.ironhack.treatmentclient.exception.IdNotFoundException;
import com.ironhack.treatmentclient.model.Treatment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
class TreatmentServiceTest {

    @Autowired
    private TreatmentService treatmentService;

    @Test
    void save() {
        Treatment treatment =   treatment = new Treatment(Type.INMUNOTHERAPY,50, 2);
        assertEquals(50.00, treatmentService.save(treatment).getDose());
    }

    @Test
    void findByPatient() {
        assertEquals(2, treatmentService.findByPatient(1).size());
    }

    @Test
    void findById() {
        assertEquals(Type.CHEMOTHERAPY, treatmentService.findById(1).getType());
    }
    @Test
    void findById_idNotfound() {
        assertThrows(IdNotFoundException.class, () -> treatmentService.findById(450));
    }
}