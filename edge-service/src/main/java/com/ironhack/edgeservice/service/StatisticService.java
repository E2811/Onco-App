package com.ironhack.edgeservice.service;

import com.ironhack.edgeservice.controller.dto.EvaluationMV;
import com.ironhack.edgeservice.model.Patient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StatisticService {

    @Autowired
    private PatientService patientService;
    @Autowired
    private EvaluationService evaluationService;

    private static final Logger LOGGER = LogManager.getLogger(StatisticService.class);

    public Map<String, Integer> findStatistics(String filter){
        LOGGER.info("[INIT] findStatistics");
        switch (filter.toLowerCase().trim()){
            case "category":
                return findPatientsCategory();
            case "activity":
                return findPatientsActivity();
            case "symptoms":
                return findPatientsSymptoms();
            default:
                throw new IllegalArgumentException("Option not valid");
        }
    }

    public Map<String, Integer> findPatientsCategory(){
        LOGGER.info("[INIT] findPatientsByCategory");
        Map<String, Integer> number = new HashMap<>();
        patientService.findAll().forEach(patientMV -> {
            evaluationService.findCompleteEval(patientMV.getUsername()).forEach(evaluationMV1 -> {
                String key = String.valueOf(evaluationMV1.getCategory().getDescription());
                if (number.containsKey(key)) {
                    int count = number.get(key) + 1;
                    number.replace(key, count);
                } else {
                    number.put(key, 1);
                }
            });
        });
        LOGGER.info("[EXIT] findPatientsByCategory");
        return number;
    }

    public Map<String, Integer> findPatientsSymptoms(){
        LOGGER.info("[INIT] findPatientsBySymptoms");
        Map<String, Integer> number = new HashMap<>();
        patientService.findAll().forEach(patientMV -> {
            evaluationService.findCompleteEval(patientMV.getUsername()).forEach(evaluationMV1 -> {
                String key = String.valueOf(evaluationMV1.getSymptoms());
                if (number.containsKey(key)) {
                    int count = number.get(key) + 1;
                    number.replace(key, count);
                } else {
                    number.put(key, 1);
                }
            });
        });
        LOGGER.info("[EXIT] findPatientsBySymptoms");
        return number;
    }

    public Map<String, Integer> findPatientsActivity(){
        LOGGER.info("[INIT] findPatientsByActivity");
        Map<String, Integer> number = new HashMap<>();
        patientService.findAll().forEach(patientMV -> {
            evaluationService.findCompleteEval(patientMV.getUsername()).forEach(evaluationMV1 -> {
                String key = String.valueOf(evaluationMV1.getEcog());
                if (number.containsKey(key)) {
                    int count = number.get(key) + 1;
                    number.replace(key, count);
                } else {
                    number.put(key, 1);
                }
            });
        });
        LOGGER.info("[EXIT] findPatientsByActivity");
        return number;
    }
}
