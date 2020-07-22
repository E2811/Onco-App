package com.ironhack.edgeservice.controller.impl;

import com.ironhack.edgeservice.controller.dto.DoctorDto;
import com.ironhack.edgeservice.controller.dto.DoctorEvaluationDto;
import com.ironhack.edgeservice.controller.dto.DoctorMV;
import com.ironhack.edgeservice.controller.dto.EvaluationMV;
import com.ironhack.edgeservice.model.DoctorEvaluation;
import com.ironhack.edgeservice.model.PatientEvaluation;
import com.ironhack.edgeservice.service.EvaluationService;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/evaluation")
public class EvaluationController {

    @Autowired
    private EvaluationService evaluationService;


    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_PATIENT') or hasRole('ROLE_DOCTOR')")
    @PostMapping("/save")
    @ApiOperation(value = "Create a new Evaluation")
    @ResponseStatus(HttpStatus.CREATED)
    public PatientEvaluation createPatientEvaluation(@Validated @RequestBody PatientEvaluation patientEvaluation) {
        return evaluationService.create(patientEvaluation);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DOCTOR')")
    @PostMapping("/complete")
    @ApiOperation(value = "Complete the evaluation by creating a new Doctor Evaluation")
    @ResponseStatus(HttpStatus.CREATED)
    public DoctorEvaluation createDoctorEvaluation(@Validated @RequestBody DoctorEvaluationDto doctorEvaluationDto) {
        return evaluationService.createDoctorEval(doctorEvaluationDto);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_PATIENT') or hasRole('ROLE_DOCTOR')")
    @GetMapping("/find_by_patient/completed/{username}")
    @ApiOperation(value = "Find all evaluations by patient")
    @ResponseStatus(HttpStatus.OK)
    public List<EvaluationMV> findByPatientCompleted(@PathVariable String username) {
        return evaluationService.findCompleteEval(username);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_PATIENT') or hasRole('ROLE_DOCTOR')")
    @GetMapping("/find_by_patient/{username}")
    @ApiOperation(value = "Find all evaluations by patient without being completed by the doctor")
    @ResponseStatus(HttpStatus.OK)
    public List<PatientEvaluation> findByPatient(@PathVariable String username) {
        return evaluationService.findByPatient(username);
    }

}
