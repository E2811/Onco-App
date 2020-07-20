package com.ironhack.edgeservice.service;

import com.ironhack.edgeservice.client.DoctorClient;
import com.ironhack.edgeservice.config.FeignBadResponseWrapper;
import com.ironhack.edgeservice.controller.dto.DoctorDto;
import com.ironhack.edgeservice.controller.dto.DoctorMV;
import com.ironhack.edgeservice.enums.Role;
import com.ironhack.edgeservice.model.Doctor;
import com.ironhack.edgeservice.model.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.exception.HystrixBadRequestException;
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

    @HystrixCommand(fallbackMethod = "createFakeDoctor", ignoreExceptions = NullPointerException.class)
    public DoctorMV create(DoctorDto doctorDto){
        User user = new User();
        user.setPassword(doctorDto.getPassword());
        user.setUsername(doctorDto.getUsername());
        user.setRole(Role.ROLE_DOCTOR);
        user = userService.create(user);
        if(user != null) {
            Doctor doctor = new Doctor(doctorDto.getName(),user.getId(), doctorDto.getSpecialty(), doctorDto.getEmail());
            doctor = doctorClient.create(doctor);
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
        Doctor doctor = null;
        User user = null;
        try {
            doctor = doctorClient.findById(id);
            user = userService.findUserById(doctor.getUserID());
        }catch (HystrixBadRequestException e) {
            if (e instanceof FeignBadResponseWrapper) {
                throw new FeignBadResponseWrapper(((FeignBadResponseWrapper) e).getStatus(),((FeignBadResponseWrapper) e).getHeaders(),((FeignBadResponseWrapper) e).getBody());
            }
        }
        return new DoctorMV(doctor.getId(), doctor.getName(), doctor.getSpecialty(), doctor.getEmail(), user.getUsername());
    }

    public DoctorMV findFakeDoctor(Integer id){
        throw new NullPointerException("Doctor service is down. Try again Later");
    }

    @HystrixCommand(fallbackMethod = "findAllFake")
    public List<DoctorMV> findAll(){
        return doctorClient.findAll().stream().map(doctor -> {
            User user = userService.findUserById(doctor.getUserID());
            return new DoctorMV(doctor.getId(), doctor.getName(), doctor.getSpecialty(), doctor.getEmail(), user.getUsername());

        }).collect(Collectors.toList());
    }

    public List<DoctorMV> findAllFake(){
        throw new NullPointerException("Doctor service is down. Try again Later");
    }
}
