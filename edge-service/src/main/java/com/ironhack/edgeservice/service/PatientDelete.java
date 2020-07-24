package com.ironhack.edgeservice.service;

import com.ironhack.edgeservice.controller.dto.PatientMV;
import com.ironhack.edgeservice.model.DoctorEvaluation;
import com.ironhack.edgeservice.model.Patient;
import com.ironhack.edgeservice.model.Result;
import com.ironhack.edgeservice.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

    private static final Logger LOGGER = LogManager.getLogger(PatientDelete.class);

    @Transactional
    public void delete(Integer id){
        LOGGER.info("[INIT] delete patient");
        PatientMV patient = patientService.findById(id);
        User user = userService.findUserByUsername(patient.getUsername());
        evaluationService.findByPatient(patient.getUsername()).forEach(patientEvaluation -> {
            DoctorEvaluation doctorEvaluation = evaluationService.findByPatientEval(patientEvaluation.getId());
            if (doctorEvaluation!=null){
                LOGGER.info("delete doctor evaluation");
                evaluationService.deleteDoctorEval(doctorEvaluation.getId());
            }
            Result result = resultService.findByEvaluation(id);
            if (result!=null){
                LOGGER.info("delete results");
                resultService.delete(result.getId());
            }
            LOGGER.info("delete patients evaluation");
            evaluationService.deletePatientEval(patientEvaluation.getId());
        });
        userService.delete(user.getId());
        patientService.delete(id);
        LOGGER.info("[EXIT] delete patient");
    }
}
