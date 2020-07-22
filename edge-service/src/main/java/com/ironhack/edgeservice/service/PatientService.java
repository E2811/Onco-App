package com.ironhack.edgeservice.service;

import com.ironhack.edgeservice.client.PatientClient;
import com.ironhack.edgeservice.client.UserClient;
import com.ironhack.edgeservice.config.FeignBadResponseWrapper;
import com.ironhack.edgeservice.controller.dto.*;
import com.ironhack.edgeservice.enums.Role;
import com.ironhack.edgeservice.exception.IdNotFoundException;
import com.ironhack.edgeservice.model.*;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.exception.HystrixBadRequestException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientService {

    @Autowired
    private PatientClient patientClient;

    @Autowired
    private UserService userService;

    private static final Logger LOGGER = LogManager.getLogger(PatientService.class);

    @HystrixCommand(fallbackMethod = "createFakePatient", ignoreExceptions = RuntimeException.class)
    public PatientMV create(PatientDto patientDto){
        LOGGER.info("[INIT] create patient");
        User user = new User();
        user.setPassword(patientDto.getPassword());
        user.setUsername(patientDto.getUsername());
        user.setRole(Role.ROLE_PATIENT);
        LOGGER.info("create user patient");
        user = userService.create(user);
        if(user != null) {
            Patient patient = new Patient(patientDto.getName(),patientDto.getPhone(), patientDto.getEmail(), patientDto.getSex(), patientDto.getWeight(), patientDto.getHeight(),user.getId(), patientDto.getBirthday(), patientDto.getDoctor());
            patient = patientClient.create(patient);
            LOGGER.info("[EXIT] create patient");
            return new PatientMV(patient.getId(), patient.getName(), patient.getPhone(), patient.getEmail(), patient.getSex(), patient.getWeight(), patient.getHeight(), patient.getBirthday(), patient.getDoctor(), user.getUsername());
        } else {
            throw new RuntimeException("Username already exists");
        }
    }
    public PatientMV createFakePatient(PatientDto patientDto){
        throw new NullPointerException("Patient service is down. Try again Later");
    }

    @HystrixCommand(fallbackMethod = "findFakePatientId", ignoreExceptions = FeignBadResponseWrapper.class)
    public PatientMV findById(Integer id) {
        Patient patient = null;
        User user = null;
        try {
            patient = patientClient.findById(id);
        } catch (HystrixBadRequestException e) {
            if (e instanceof FeignBadResponseWrapper) {
                throw new FeignBadResponseWrapper(((FeignBadResponseWrapper) e).getStatus(), ((FeignBadResponseWrapper) e).getHeaders(), ((FeignBadResponseWrapper) e).getBody());
            }
        }
        user = userService.findUserById(patient.getUserID());
        return new PatientMV(patient.getId(), patient.getName(), patient.getPhone(), patient.getEmail(), patient.getSex(), patient.getWeight(), patient.getHeight(), patient.getBirthday(), patient.getDoctor(), user.getUsername());
    }

    public PatientMV findFakePatientId(Integer id){
        throw new NullPointerException("Patient service is down. Try again Later");
    }

    @HystrixCommand(fallbackMethod = "findFakePatient", ignoreExceptions = IdNotFoundException.class)
    public PatientMV findByUsername(String name) {
        LOGGER.info("[INIT] find patient by username");
        Patient patient = null;
        LOGGER.info("find  user patient by username");
        User user = userService.findUserByUsername(name);
        if (user== null){
            throw new IdNotFoundException("Patient with username "+ name+ " not found");
        }
        LOGGER.info("find  patient by userId");
        patient = patientClient.findByUserId(user.getId());
        LOGGER.info("[EXIT] find patient by username");
        return new PatientMV(patient.getId(), patient.getName(), patient.getPhone(), patient.getEmail(), patient.getSex(), patient.getWeight(), patient.getHeight(), patient.getBirthday(), patient.getDoctor(), user.getUsername());
    }

    public PatientMV findFakePatient(String name){
        throw new NullPointerException("Patient service is down. Try again Later");
    }

    @HystrixCommand(fallbackMethod = "findAllFake")
    public List<PatientMV> findAll(){
        LOGGER.info("[INIT] find list patients");
        return patientClient.findAll().stream().map(patient -> {
            User user = userService.findUserById(patient.getUserID());
            return new PatientMV(patient.getId(), patient.getName(), patient.getPhone(), patient.getEmail(), patient.getSex(), patient.getWeight(), patient.getHeight(), patient.getBirthday(), patient.getDoctor(), user.getUsername());

        }).collect(Collectors.toList());
    }

    public List<PatientMV> findAllFake(){
        throw new NullPointerException("Patient service is down. Try again Later");
    }

    @HystrixCommand(fallbackMethod = "updateFake")
    public void update(PatientWeight patientWeight){
        LOGGER.info("[INIT] update patient weight");
        patientClient.update(patientWeight);
        LOGGER.info("[EXIT] update patient weight");
    }

    public void updateFake(PatientWeight patientWeight) {
        throw new NullPointerException("Patient service is down. Try again Later");
    }

    @HystrixCommand(fallbackMethod = "deleteFake")
    public void delete(Integer id){
        LOGGER.info("[INIT] delete patient by it sid");
        patientClient.delete(id);
        LOGGER.info("[EXIT] delete patient by its id");
    }
    public void deleteFake(Integer id) {
        throw new NullPointerException("Patient service is down. Try again Later");
    }

    @HystrixCommand(fallbackMethod = "findByDoctorFake")
    public List<PatientMV> findAllByDoctor(Integer id){
        LOGGER.info("[INIT] find list patients by doctor");
        return patientClient.findByDoctor(id).stream().map(patient -> {
            User user = userService.findUserById(patient.getUserID());
            return new PatientMV(patient.getId(), patient.getName(), patient.getPhone(), patient.getEmail(), patient.getSex(), patient.getWeight(), patient.getHeight(), patient.getBirthday(), patient.getDoctor(), user.getUsername());

        }).collect(Collectors.toList());
    }

    public List<PatientMV> findByDoctorFake(Integer id){
        throw new NullPointerException("Patient service is down. Try again Later");
    }
}
