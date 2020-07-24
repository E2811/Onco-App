package com.ironhack.treatmentclient.controller.impl;

import com.ironhack.treatmentclient.model.Treatment;
import com.ironhack.treatmentclient.service.TreatmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class TreatmentController {

    @Autowired
    private TreatmentService treatmentService;

    @PostMapping("/treatment/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Treatment create(@RequestBody @Valid Treatment treatment) {
        return treatmentService.save(treatment);
    }

    @GetMapping("/treatment/byPatient/{patientId}")
    @ResponseStatus(HttpStatus.OK)
    public List<Treatment> findByPatient(@PathVariable Integer patientId) {
        return treatmentService.findByPatient(patientId);
    }

    @DeleteMapping("/treatment")
    @ResponseStatus(HttpStatus.OK)
    public void findById(@RequestBody Treatment treatment) {
        treatmentService.delete(treatment);
    }

}
