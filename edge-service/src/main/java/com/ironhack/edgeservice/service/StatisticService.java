package com.ironhack.edgeservice.service;

import com.ironhack.edgeservice.controller.dto.EvaluationMV;
import com.ironhack.edgeservice.model.Patient;
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

    public Map<String, Integer> findStatistics(String filter){
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
        Map<String, Integer> number = new HashMap<>();
        patientService.findAll().forEach(patientMV -> {
            evaluationService.findCompleteEval(patientMV.getId()).forEach(evaluationMV1 -> {
                String key = String.valueOf(evaluationMV1.getCategory().getDescription());
                if (number.containsKey(key)) {
                    int count = number.get(key) + 1;
                    number.replace(key, count);
                } else {
                    number.put(key, 1);
                }
            });
        });
        return number;
    }

    public Map<String, Integer> findPatientsSymptoms(){
        Map<String, Integer> number = new HashMap<>();
        patientService.findAll().forEach(patientMV -> {
            evaluationService.findCompleteEval(patientMV.getId()).forEach(evaluationMV1 -> {
                String key = String.valueOf(evaluationMV1.getSymptoms());
                if (number.containsKey(key)) {
                    int count = number.get(key) + 1;
                    number.replace(key, count);
                } else {
                    number.put(key, 1);
                }
            });
        });
        return number;
    }

    public Map<String, Integer> findPatientsActivity(){
        Map<String, Integer> number = new HashMap<>();
        patientService.findAll().forEach(patientMV -> {
            evaluationService.findCompleteEval(patientMV.getId()).forEach(evaluationMV1 -> {
                String key = String.valueOf(evaluationMV1.getEcog());
                if (number.containsKey(key)) {
                    int count = number.get(key) + 1;
                    number.replace(key, count);
                } else {
                    number.put(key, 1);
                }
            });
        });
        return number;
    }
}
