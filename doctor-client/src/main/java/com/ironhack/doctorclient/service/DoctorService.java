package com.ironhack.doctorclient.service;

import com.ironhack.doctorclient.exception.IdNotFoundException;
import com.ironhack.doctorclient.model.Doctor;
import com.ironhack.doctorclient.repository.DoctorRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.util.List;

@Service
public class DoctorService {

    private static final Logger LOGGER = LogManager.getLogger(DoctorService.class);
    @Autowired
    private DoctorRepository doctorRepository;

    public Doctor findById(Integer id){
        LOGGER.info("[INIT] find doctors by its id");
        return doctorRepository.findById(id).orElseThrow(()-> new IdNotFoundException("Doctor with id "+ id +" not found."));
    }

    public List<Doctor> findAll(){
        LOGGER.info("[INIT] find all doctors");
        return doctorRepository.findAll();
    }

    public Doctor save(Doctor doctor){
        LOGGER.info("[INIT] create a new doctor");
        LOGGER.info("[EXIT] create a new doctor");
        return doctorRepository.save(doctor);
    }
}
