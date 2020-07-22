package com.ironhack.evaluationclient.controller;

import com.ironhack.evaluationclient.model.PatientEvaluation;
import com.ironhack.evaluationclient.service.PatientEvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class PatientEvaluationController {

    @Autowired
    private PatientEvaluationService patientEvaluationService;


    @PostMapping("/patient-evaluation/save")
    @ResponseStatus(HttpStatus.CREATED)
    public PatientEvaluation create(@RequestBody @Valid PatientEvaluation patientEvaluation) {
        return patientEvaluationService.create(patientEvaluation);
    }

    @GetMapping("/patient-evaluation/byPatient/{patientId}")
    @ResponseStatus(HttpStatus.OK)
    public List<PatientEvaluation> findByPatient(@PathVariable Integer patientId) {
        return patientEvaluationService.findByPatient(patientId);
    }

    @GetMapping("/patient-evaluation/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PatientEvaluation findById(@PathVariable Integer id) {
        return patientEvaluationService.findById(id);
    }

    @DeleteMapping("/patient-evaluation/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id){
        patientEvaluationService.delete(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Integer id){
        patientEvaluationService.update(id);
    }

}
