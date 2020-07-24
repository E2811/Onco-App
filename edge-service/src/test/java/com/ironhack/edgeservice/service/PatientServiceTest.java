package com.ironhack.edgeservice.service;

import com.ironhack.edgeservice.client.PatientClient;
import com.ironhack.edgeservice.config.FeignBadResponseWrapper;
import com.ironhack.edgeservice.controller.dto.PatientDto;
import com.ironhack.edgeservice.controller.dto.PatientMV;
import com.ironhack.edgeservice.controller.dto.PatientWeight;
import com.ironhack.edgeservice.enums.Role;
import com.ironhack.edgeservice.enums.Sex;
import com.ironhack.edgeservice.model.Doctor;
import com.ironhack.edgeservice.model.Patient;
import com.ironhack.edgeservice.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest
class PatientServiceTest {

    @MockBean
    private UserService userService;
    @MockBean
    private PatientClient patientClient;
    @Autowired
    private PatientService patientService;

    private Patient patient;
    private PatientDto patientDto;
    private PatientMV patientMV;

    @BeforeEach
    void setUp(){
        patient = new Patient("Manuel Pérez", "698547148", "manu@gmail.com", Sex.M,82, 180,1, LocalDate.of(1965,5,12), 4);
        patientDto = new  PatientDto("Manuel Pérez", "698547148", "manu@gmail.com", Sex.M,82, 180, LocalDate.of(1965,5,12), 1,"manuelPerez", "montaña");
        patientMV = new PatientMV(1,"Manuel Pérez", "698547148", "manu@gmail.com", Sex.M,82, 180, LocalDate.of(1965,5,12), 1,"manuelPerez");
        User user= new User(1, "manuelPerez", "barco", Role.ROLE_PATIENT);
        when(patientClient.findById(1)).thenReturn(patient);
        when(userService.findUserById(1)).thenReturn(user);
        when(userService.findUserByUsername("manuelPérez")).thenReturn(user);
        when(patientClient.findByUserId(1)).thenReturn(patient);
        when(patientClient.findAll()).thenReturn(Arrays.asList(patient));
        when(patientClient.findByDoctor(4)).thenReturn(Arrays.asList(patient));
        when(patientClient.create(patient)).thenReturn(patient);
        when(patientClient.create(Mockito.any(Patient.class))).thenAnswer(i -> i.getArguments()[0]);
        when(userService.create(Mockito.any(User.class))).thenReturn(user);
        doNothing().when(patientClient).delete(1);
        when(patientClient.findById(36)).thenThrow(FeignBadResponseWrapper.class);
        doNothing().when(patientClient).update(new PatientWeight(3,60));
    }

    @Test
    void create() {
        assertEquals("Manuel Pérez", patientService.create(patientDto).getName());
    }

    @Test
    void createFakePatient() {
        assertThrows(NullPointerException.class, ()-> patientService.createFakePatient(patientDto));
    }
    @Test
    void findByIDFeign() {
        assertThrows(FeignBadResponseWrapper.class, ()-> patientService.findById(36));
    }


    @Test
    void findById() {
        assertEquals("Manuel Pérez", patientService.findById(1).getName());
    }

    @Test
    void findFakePatientId() {
        assertThrows(NullPointerException.class, ()-> patientService.findFakePatientId(40));
    }

    @Test
    void findByUsername() {
        assertEquals("Manuel Pérez", patientService.findByUsername("manuelPérez").getName());
    }

    @Test
    void findFakePatient() {
        assertThrows(NullPointerException.class, ()-> patientService.findFakePatient("hello"));
    }

    @Test
    void findAll() {
        assertEquals(1, patientService.findAll().size());
    }

    @Test
    void findAllFake() {
        assertThrows(NullPointerException.class, ()-> patientService.findAllFake());
    }

    @Test
    void update() {
        patientService.update(new PatientWeight(3,60));
    }

    @Test
    void updateFake() {
        assertThrows(NullPointerException.class, ()-> patientService.updateFake(new PatientWeight(3,60)));
    }

    @Test
    void delete() {
        patientService.delete(1);
    }

    @Test
    void deleteFake() {
        assertThrows(NullPointerException.class, ()-> patientService.deleteFake(3));
    }

    @Test
    void findAllByDoctor() {
        assertEquals(1, patientService.findAllByDoctor(4).size());
    }

    @Test
    void findByDoctorFake() {
        assertThrows(NullPointerException.class, ()-> patientService.findByDoctorFake(987));
    }
}