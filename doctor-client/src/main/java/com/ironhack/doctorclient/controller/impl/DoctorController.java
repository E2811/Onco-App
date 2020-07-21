package com.ironhack.doctorclient.controller.impl;

import com.ironhack.doctorclient.model.Doctor;
import com.ironhack.doctorclient.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.print.Doc;
import javax.validation.Valid;
import java.util.List;

@RestController
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @PostMapping("/doctor/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Doctor create(@RequestBody @Valid Doctor doctor) {
        return doctorService.save(doctor);
    }

    @GetMapping("/doctors")
    @ResponseStatus(HttpStatus.OK)
    public List<Doctor> findAll() {
        return doctorService.findAll();
    }

    @GetMapping("/doctor/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Doctor findById(@PathVariable Integer id) {
        return doctorService.findById(id);
    }

    @GetMapping("/doctor/by_user_id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Doctor findByUser(@PathVariable Integer id) {
        return doctorService.findByUser(id);
    }
}
