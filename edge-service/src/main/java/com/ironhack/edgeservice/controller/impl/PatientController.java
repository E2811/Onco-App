package com.ironhack.edgeservice.controller.impl;

import com.ironhack.edgeservice.controller.dto.*;
import com.ironhack.edgeservice.model.Patient;
import com.ironhack.edgeservice.service.PatientDelete;
import com.ironhack.edgeservice.service.PatientService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/patient")
public class PatientController {
    @Autowired
    private PatientService patientService;
    @Autowired
    private PatientDelete patientDelete;

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DOCTOR')")
    @PostMapping("/save")
    @ApiOperation(value = "Create a new Patient")
    @ResponseStatus(HttpStatus.CREATED)
    public PatientMV createPatient(@Validated @RequestBody PatientDto patientDto) {
        return patientService.create(patientDto);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DOCTOR')")
    @GetMapping("/findAll")
    @ApiOperation(value = "Find all patients")
    @ResponseStatus(HttpStatus.OK)
    public List<PatientMV> findAllPatients() {
        return patientService.findAll();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DOCTOR')")
    @GetMapping("/find_by_doctor/{id}")
    @ApiOperation(value = "Find all patients by doctor")
    @ResponseStatus(HttpStatus.OK)
    public List<PatientMV> findByDoctor(@PathVariable Integer id) {
        return patientService.findAllByDoctor(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DOCTOR') or hasRole('ROLE_PATIENT')")
    @GetMapping("/find_by_id/{id}")
    @ApiOperation(value = "find a patient by its id")
    @ResponseStatus(HttpStatus.OK)
    public PatientMV findById(@PathVariable Integer id) {
        return patientService.findById(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DOCTOR') or hasRole('ROLE_PATIENT')")
    @GetMapping("/find_by_username/{name}")
    @ApiOperation(value = "find a patient by its username")
    @ResponseStatus(HttpStatus.OK)
    public PatientMV findByUsername(@PathVariable String name) {
        return patientService.findByUsername(name);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DOCTOR') or hasRole('ROLE_PATIENT')")
    @PutMapping("")
    @ApiOperation(value = "update the weight of a patient")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody @Valid PatientWeight patientWeight) {
        patientService.update(patientWeight);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DOCTOR')")
    @DeleteMapping("/{id}")
    @ApiOperation(value = "delete a patient by its id")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        patientDelete.delete(id);
    }
}
