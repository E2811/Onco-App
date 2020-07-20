package com.ironhack.edgeservice.service;

import com.ironhack.edgeservice.client.PatientClient;
import com.ironhack.edgeservice.client.UserClient;
import com.ironhack.edgeservice.config.FeignBadResponseWrapper;
import com.ironhack.edgeservice.controller.dto.*;
import com.ironhack.edgeservice.enums.Role;
import com.ironhack.edgeservice.model.*;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.exception.HystrixBadRequestException;
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

    @HystrixCommand(fallbackMethod = "createFakePatient", ignoreExceptions = RuntimeException.class)
    public PatientMV create(PatientDto patientDto){
        User user = new User();
        user.setPassword(patientDto.getPassword());
        user.setUsername(patientDto.getUsername());
        user.setRole(Role.ROLE_DOCTOR);
        user = userService.create(user);
        if(user != null) {
            Patient patient = new Patient(patientDto.getName(),patientDto.getPhone(), patientDto.getEmail(), patientDto.getSex(), patientDto.getWeight(), patientDto.getHeight(),user.getId(), patientDto.getBirthday(), patientDto.getDoctor());
            patient = patientClient.create(patient);
            return new PatientMV(patient.getId(), patient.getName(), patient.getPhone(), patient.getEmail(), patient.getSex(), patient.getWeight(), patient.getHeight(), patient.getBirthday(), patient.getDoctor(), user.getUsername());
        } else {
            throw new RuntimeException("Username already exists");
        }
    }
    public PatientMV createFakePatient(PatientDto patientDto){
        throw new NullPointerException("Patient service is down. Try again Later");
    }

    @HystrixCommand(fallbackMethod = "findFakePatient", ignoreExceptions = FeignBadResponseWrapper.class)
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

    public PatientMV findFakePatient(Integer id){
        throw new NullPointerException("Patient service is down. Try again Later");
    }

    @HystrixCommand(fallbackMethod = "findAllFake")
    public List<PatientMV> findAll(){
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
        patientClient.update(patientWeight);
    }

    public void updateFake(PatientWeight patientWeight) {
        throw new NullPointerException("Patient service is down. Try again Later");
    }

    @HystrixCommand(fallbackMethod = "deleteFake")
    public void delete(Integer id){
        patientClient.delete(id);
    }
    public void deleteFake(Integer id) {
        throw new NullPointerException("Patient service is down. Try again Later");
    }
}
