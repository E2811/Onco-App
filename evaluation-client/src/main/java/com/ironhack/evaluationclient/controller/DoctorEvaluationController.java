package com.ironhack.evaluationclient.controller;

import com.ironhack.evaluationclient.controller.dto.DoctorEvaluationDto;
import com.ironhack.evaluationclient.model.DoctorEvaluation;
import com.ironhack.evaluationclient.model.PatientEvaluation;
import com.ironhack.evaluationclient.service.DoctorEvaluationService;
import com.ironhack.evaluationclient.service.PatientEvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class DoctorEvaluationController {

    @Autowired
    private DoctorEvaluationService doctorEvaluationService;


    @PostMapping("/doctor-evaluation/save")
    @ResponseStatus(HttpStatus.CREATED)
    public DoctorEvaluation createDoctorEval(@RequestBody @Valid DoctorEvaluationDto doctorEvaluationDto) {
        return doctorEvaluationService.create(doctorEvaluationDto);
    }

    @GetMapping("/doctor-evaluation/associated/{evaluationId}")
    @ResponseStatus(HttpStatus.OK)
    public DoctorEvaluation findByEvaluation(@PathVariable Integer evaluationId) {
        return doctorEvaluationService.findByEvaluation(evaluationId);
    }

    @GetMapping("/doctor-evaluation/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DoctorEvaluation findByIdDoctorEval(@PathVariable Integer id) {
        return doctorEvaluationService.findById(id);
    }

    @DeleteMapping("/doctor-evaluation/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDoctorEvaluation(@PathVariable Integer id){
        doctorEvaluationService.delete(id);
    }
}
