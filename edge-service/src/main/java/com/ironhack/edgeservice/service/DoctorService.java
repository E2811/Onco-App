package com.ironhack.edgeservice.service;

import com.ironhack.edgeservice.client.DoctorClient;
import com.ironhack.edgeservice.config.FeignBadResponseWrapper;
import com.ironhack.edgeservice.controller.dto.DoctorDto;
import com.ironhack.edgeservice.controller.dto.DoctorMV;
import com.ironhack.edgeservice.controller.dto.PatientMV;
import com.ironhack.edgeservice.enums.Role;
import com.ironhack.edgeservice.exception.IdNotFoundException;
import com.ironhack.edgeservice.model.Doctor;
import com.ironhack.edgeservice.model.Patient;
import com.ironhack.edgeservice.model.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.exception.HystrixBadRequestException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DoctorService {

    @Autowired
    private DoctorClient doctorClient;
    @Autowired
    private UserService userService;

    private static final Logger LOGGER = LogManager.getLogger(DoctorService.class);

    @HystrixCommand(fallbackMethod = "createFakeDoctor", ignoreExceptions = NullPointerException.class)
    public DoctorMV create(DoctorDto doctorDto){
        LOGGER.info("[INIT] Create a doctor");
        User user = new User();
        user.setPassword(doctorDto.getPassword());
        user.setUsername(doctorDto.getUsername());
        user.setRole(Role.ROLE_DOCTOR);
        LOGGER.info("Check that user doesn't exist");
        user = userService.create(user);
        if(user != null) {
            Doctor doctor = new Doctor(doctorDto.getName(),user.getId(), doctorDto.getSpecialty(), doctorDto.getEmail());
            doctor = doctorClient.create(doctor);
            LOGGER.info("[EXIT] Create a doctor");
            return new DoctorMV(doctor.getId(), doctor.getName(), doctor.getSpecialty(), doctor.getEmail(), user.getUsername());
        } else {
            throw new RuntimeException("Username already exists");
        }
    }

    public DoctorMV createFakeDoctor(DoctorDto doctorDto){
        throw new NullPointerException("Doctor service is down. Try again Later");
    }

    @HystrixCommand(fallbackMethod = "findFakeDoctor", ignoreExceptions = FeignBadResponseWrapper.class)
    public DoctorMV findById(Integer id){
        LOGGER.info("[INIT] find a doctor by its id");
        Doctor doctor = null;
        User user = null;
        try {
            doctor = doctorClient.findById(id);
            LOGGER.info("Get doctor user");
            user = userService.findUserById(doctor.getUserID());
        }catch (HystrixBadRequestException e) {
            if (e instanceof FeignBadResponseWrapper) {
                throw new FeignBadResponseWrapper(((FeignBadResponseWrapper) e).getStatus(),((FeignBadResponseWrapper) e).getHeaders(),((FeignBadResponseWrapper) e).getBody());
            }
        }
        LOGGER.info("[EXIT] find a doctor by its id");
        return new DoctorMV(doctor.getId(), doctor.getName(), doctor.getSpecialty(), doctor.getEmail(), user.getUsername());
    }

    public DoctorMV findFakeDoctor(Integer id){
        throw new NullPointerException("Doctor service is down. Try again Later");
    }

    @HystrixCommand(fallbackMethod = "findAllFake")
    public List<DoctorMV> findAll(){
        LOGGER.info("[INIT] find all doctors");
        return doctorClient.findAll().stream().map(doctor -> {
            User user = userService.findUserById(doctor.getUserID());
            return new DoctorMV(doctor.getId(), doctor.getName(), doctor.getSpecialty(), doctor.getEmail(), user.getUsername());

        }).collect(Collectors.toList());
    }

    public List<DoctorMV> findAllFake(){
        throw new NullPointerException("Doctor service is down. Try again Later");
    }

    @HystrixCommand(fallbackMethod = "findFakeDoctorUser", ignoreExceptions = IdNotFoundException.class)
    public DoctorMV findByUsername(String name) {
        LOGGER.info("[INIT] find doctor by username");
        Doctor doctor = null;
        LOGGER.info("find  user patient by username");
        User user = userService.findUserByUsername(name);
        if (user== null){
            throw new IdNotFoundException("Doctor with username "+ name+ " not found");
        }
        LOGGER.info("find  doctor by userId");
        doctor = doctorClient.findByUser(user.getId());
        LOGGER.info("[EXIT] find doctor by username");
        return new DoctorMV(doctor.getId(), doctor.getName(), doctor.getSpecialty(), doctor.getEmail(), user.getUsername());
    }

    public DoctorMV findFakeDoctorUser(String name){
        throw new NullPointerException("Doctor service is down. Try again Later");
    }
}
