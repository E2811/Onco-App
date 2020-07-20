package com.ironhack.doctorclient.service;

import com.ironhack.doctorclient.enums.Specialty;
import com.ironhack.doctorclient.exception.IdNotFoundException;
import com.ironhack.doctorclient.model.Doctor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.print.Doc;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
class DoctorServiceTest {

    @Autowired
    private DoctorService doctorService;

    @Test
    void findById() throws IdNotFoundException {
        Doctor doctor = doctorService.findById(1);
        assertEquals("Joana Cruz", doctor.getName());
    }

    @Test
    void findById_idNotfound() {
        assertThrows(IdNotFoundException.class, () -> doctorService.findById(450));
    }

    @Test
    void findAll() {
        assertEquals(6, doctorService.findAll().size());
    }

    @Test
    void save() {
        Doctor doctor = new Doctor("Gema",7, Specialty.PEDIATRICIAN,"gema@gmail.com");
        assertEquals("Gema", doctorService.save(doctor).getName());
    }
}