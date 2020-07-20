package com.ironhack.edgeservice.service;

import com.ironhack.edgeservice.client.DoctorClient;
import com.ironhack.edgeservice.controller.dto.DoctorDto;
import com.ironhack.edgeservice.controller.dto.DoctorMV;
import com.ironhack.edgeservice.enums.Role;
import com.ironhack.edgeservice.enums.Specialty;
import com.ironhack.edgeservice.model.Doctor;
import com.ironhack.edgeservice.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.print.Doc;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class DoctorServiceTest {

    @MockBean
    private DoctorClient doctorClient;
    @Autowired
    private DoctorService doctorService;
    @MockBean
    private UserService userService;

    private Doctor doctor;
    private DoctorDto doctorDto;
    private DoctorMV doctorMV;
    private User user;

    @BeforeEach
    void setUp() {
        doctor = new Doctor("Gema Montalvo", 1, Specialty.PEDIATRICIAN, "gema@gmail.com");
        doctor.setId(1);
        user = new User(1,"gemaMontalvo", "blueWater", Role.ROLE_DOCTOR);
        doctorDto = new DoctorDto("Gema Montalvo",  Specialty.PEDIATRICIAN, "gema@gmail.com", "gemaMontalvo", "blueWater");
        doctorMV = new DoctorMV(doctor.getId(),"Gema Montalvo", Specialty.PEDIATRICIAN, "gema@gmail.com" , "gemaMontalvo");
        when(doctorClient.findById(1)).thenReturn(doctor);
        when(userService.findUserById(1)).thenReturn(user);
        when(doctorClient.findAll()).thenReturn(Arrays.asList(doctor));
        when(doctorClient.create(Mockito.any(Doctor.class))).thenAnswer(i -> i.getArguments()[0]);
        when(userService.create(Mockito.any(User.class))).thenReturn(user);

    }

    @Test
    void create() {
        assertEquals("Gema Montalvo", doctorService.create(doctorDto).getName());
    }

    @Test
    void createFakeDoctor() {
        assertThrows(NullPointerException.class, ()-> doctorService.createFakeDoctor(doctorDto));
    }

    @Test
    void findById() {
        assertEquals("Gema Montalvo", doctorService.findById(1).getName());
    }

    @Test
    void findFakeDoctor() {
        assertThrows(NullPointerException.class, ()-> doctorService.findFakeDoctor(1));
    }

    @Test
    void findAll() {
        assertEquals(1, doctorService.findAll().size());
    }

    @Test
    void findAllFake() {
        assertThrows(NullPointerException.class, ()-> doctorService.findAllFake());
    }
}