package com.ironhack.edgeservice.controller.impl;

import com.ironhack.edgeservice.controller.dto.DoctorDto;
import com.ironhack.edgeservice.controller.dto.DoctorMV;
import com.ironhack.edgeservice.controller.dto.PatientMV;
import com.ironhack.edgeservice.service.DoctorService;
import com.ironhack.edgeservice.service.StatisticService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;
    @Autowired
    private StatisticService statisticService;


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

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DOCTOR')")
    @GetMapping("/statistics/{filter}")
    @ApiOperation(value = "find statistics by filter activity|symptoms|category")
    @ResponseStatus(HttpStatus.OK)
    public Map<String,Integer> findByFilter(@PathVariable String filter) {
        return statisticService.findStatistics(filter);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DOCTOR')")
    @GetMapping("/find_by_username/{name}")
    @ApiOperation(value = "find a doctor by its username")
    @ResponseStatus(HttpStatus.OK)
    public DoctorMV findByUsername(@PathVariable String name) {
        return doctorService.findByUsername(name);
    }


}
