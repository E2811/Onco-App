package com.ironhack.doctorclient.service;

import com.ironhack.doctorclient.controller.dto.DoctorDto;
import com.ironhack.doctorclient.exception.IdNotFoundException;
import com.ironhack.doctorclient.model.Doctor;
import com.ironhack.doctorclient.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.util.List;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    public Doctor findById(Integer id){
        return doctorRepository.findById(id).orElseThrow(()-> new IdNotFoundException("Doctor with id "+ id +" not found."));
    }

    public List<Doctor> findAll(){
        return doctorRepository.findAll();
    }

    public Doctor save(DoctorDto doctorDto){
        Doctor doctor = new Doctor(doctorDto.getName(),doctorDto.getUserID(), doctorDto.getSpecialty(),doctorDto.getEmail());
        return doctorRepository.save(doctor);
    }
}
