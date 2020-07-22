package com.ironhack.edgeservice.client;

import com.ironhack.edgeservice.controller.dto.PatientWeight;
import com.ironhack.edgeservice.model.Patient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@FeignClient(name ="patient-client", url = "https://onco-app-patient-client.herokuapp.com/")
public interface PatientClient {

    @PostMapping("/patient/save")
    public Patient create(@RequestBody @Valid Patient patient);

    @GetMapping("/patient/{id}")
    public Patient findById(@PathVariable Integer id);

    @GetMapping("/patient/by-doctor/{id}")
    public List<Patient> findByDoctor(@PathVariable Integer id);

    @GetMapping("/patients")
    public List<Patient> findAll();

    @DeleteMapping("/patient/{id}")
    public void delete(@PathVariable Integer id);

    @PutMapping("/patient")
    public void update(@RequestBody @Valid PatientWeight patientWeight);

    @GetMapping("/patient/find_by_user/{id}")
    public Patient findByUserId(@PathVariable Integer id);

}
