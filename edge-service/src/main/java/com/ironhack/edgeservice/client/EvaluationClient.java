package com.ironhack.edgeservice.client;

import com.ironhack.edgeservice.controller.dto.DoctorEvaluationDto;
import com.ironhack.edgeservice.model.DoctorEvaluation;
import com.ironhack.edgeservice.model.PatientEvaluation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@FeignClient(name = "evaluation-client")
public interface EvaluationClient {

    @PostMapping("/patient-evaluation/save")
    public PatientEvaluation create(@RequestBody @Valid PatientEvaluation patientEvaluation);

    @GetMapping("/patient-evaluation/byPatient/{patientId}")
    public List<PatientEvaluation> findByPatient(@PathVariable Integer patientId);

    @GetMapping("/patient-evaluation/{id}")
    public PatientEvaluation findById(@PathVariable Integer id);

    @DeleteMapping("/patient-evaluation/{id}")
    public void delete(@PathVariable Integer id);

    @PostMapping("/doctor-evaluation/save")
    public DoctorEvaluation createDoctorEval(@RequestBody @Valid DoctorEvaluationDto doctorEvaluation);

    @GetMapping("/doctor-evaluation/associated/{evaluationId}")
    public DoctorEvaluation findByEvaluation(@PathVariable Integer evaluationId);

    @GetMapping("/doctor-evaluation/{id}")
    public DoctorEvaluation findByIdDoctorEval(@PathVariable Integer id);

    @DeleteMapping("/doctor-evaluation/{id}")
    public void deleteDoctorEvaluation(@PathVariable Integer id);

    @PutMapping("/{id}")
    public void update(@PathVariable Integer id);
}
