package com.ironhack.edgeservice.controller.impl;

import com.ironhack.edgeservice.controller.dto.DoctorDto;
import com.ironhack.edgeservice.controller.dto.DoctorMV;
import com.ironhack.edgeservice.service.DoctorService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/save")
    @ApiOperation(value = "Create a new Doctor")
    @ResponseStatus(HttpStatus.CREATED)
    public DoctorMV createDoctor(@Validated @RequestBody DoctorDto doctorDto) {
        return doctorService.create(doctorDto);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_PATIENT') or hasRole('ROLE_DOCTOR')")
    @GetMapping("/findAll")
    @ApiOperation(value = "Find all doctors")
    @ResponseStatus(HttpStatus.OK)
    public List<DoctorMV> findAllDoctors() {
        return doctorService.findAll();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_PATIENT') or hasRole('ROLE_DOCTOR')")
    @GetMapping("/find_by_id/{id}")
    @ApiOperation(value = "find a doctor by id")
    @ResponseStatus(HttpStatus.OK)
    public DoctorMV findById(@PathVariable Integer id) {
        return doctorService.findById(id);
    }


}
