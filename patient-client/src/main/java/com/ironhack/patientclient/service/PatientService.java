package com.ironhack.patientclient.service;

import com.google.inject.internal.asm.$TypePath;
import com.ironhack.patientclient.controller.dto.PatientWeight;
import com.ironhack.patientclient.exception.IdNotFoundException;
import com.ironhack.patientclient.model.Patient;
import com.ironhack.patientclient.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    public List<Patient> findAll(){
        return patientRepository.findAll();
    }
    public List<Patient> findByDoctor(Integer id){
        return patientRepository.findByDoctor(id);
    }

    public Patient findById(Integer id){
        return patientRepository.findById(id).orElseThrow(()-> new IdNotFoundException("Patient with id "+id+" nnot found"));
    }

    public Patient findByUserId(Integer id){
        return patientRepository.findByUserID(id);
    }

    public Patient create(Patient patient){
        return patientRepository.save(patient);
    }

    public void delete(Integer id){
        patientRepository.deleteById(id);
    }

    public void update(PatientWeight patientWeight){
        Patient patient = patientRepository.findById(patientWeight.getId()).orElseThrow(()-> new IdNotFoundException("Patient with id "+patientWeight.getId()+" nnot found"));
        patient.setWeight(patientWeight.getWeight());
        patientRepository.save(patient);
    }
}
