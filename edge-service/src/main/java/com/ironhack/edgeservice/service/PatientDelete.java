package com.ironhack.edgeservice.service;

import com.ironhack.edgeservice.controller.dto.PatientMV;
import com.ironhack.edgeservice.model.DoctorEvaluation;
import com.ironhack.edgeservice.model.Patient;
import com.ironhack.edgeservice.model.Result;
import com.ironhack.edgeservice.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class PatientDelete {

    @Autowired
    private PatientService patientService;
    @Autowired
    private EvaluationService evaluationService;
    @Autowired
    private ResultService resultService;
    @Autowired
    private UserService userService;

    @Transactional
    public void delete(Integer id){
        PatientMV patient = patientService.findById(id);
        User user = userService.findUserByUsername(patient.getUsername());
        userService.delete(user.getId());
        evaluationService.findByPatient(patient.getId()).forEach(patientEvaluation -> {
            System.out.println(patientEvaluation);
            DoctorEvaluation doctorEvaluation = evaluationService.findByPatientEval(patientEvaluation.getId());
            System.out.println(doctorEvaluation);
            if (doctorEvaluation!=null){
                evaluationService.deleteDoctorEval(doctorEvaluation.getId());
            }
            Result result = resultService.findByEvaluation(id);
            if (result!=null){
                resultService.delete(result.getId());
            }
            evaluationService.deletePatientEval(patientEvaluation.getId());
        });
        patientService.delete(id);
    }
}
