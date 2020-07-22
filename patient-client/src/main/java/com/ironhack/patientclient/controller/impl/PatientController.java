package com.ironhack.patientclient.controller.impl;

import com.ironhack.patientclient.controller.dto.PatientWeight;
import com.ironhack.patientclient.model.Patient;
import com.ironhack.patientclient.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class PatientController {

    @Autowired
    private PatientService patientService;

    @PostMapping("/patient/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Patient create(@RequestBody @Valid Patient patient) {
        return patientService.create(patient);
    }
    //bo
    @GetMapping("/patient/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Patient findById(@PathVariable Integer id){
        return patientService.findById(id);
    }

    @GetMapping("/patient/find_by_user/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Patient findByUserId(@PathVariable Integer id){
        return patientService.findByUserId(id);
    }

    @GetMapping("/patient/by-doctor/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Patient> findByDoctor(@PathVariable Integer id){
        return patientService.findByDoctor(id);
    }

    @GetMapping("/patients")
    @ResponseStatus(HttpStatus.OK)
    public List<Patient> findAll(){
        return patientService.findAll();
    }

    @DeleteMapping("/patient/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id){
        patientService.delete(id);
    }

    @PutMapping("/patient")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody @Valid PatientWeight patientWeight){
        patientService.update(patientWeight);
    }
}
