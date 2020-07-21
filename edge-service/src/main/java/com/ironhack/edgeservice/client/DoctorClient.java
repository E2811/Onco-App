package com.ironhack.edgeservice.client;

import com.ironhack.edgeservice.model.Doctor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@FeignClient(name = "doctor-client")
public interface DoctorClient {

    @PostMapping("/doctor/save")
    public Doctor create(@RequestBody @Valid Doctor doctor);

    @GetMapping("/doctors")
    public List<Doctor> findAll();

    @GetMapping("/doctor/{id}")
    public Doctor findById(@PathVariable Integer id);

    @GetMapping("/doctor/by_user_id/{id}")
    public Doctor findByUser(@PathVariable Integer id);
}
