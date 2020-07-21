package com.ironhack.evaluationclient.service;

import com.ironhack.evaluationclient.exception.IdNotFoundException;
import com.ironhack.evaluationclient.model.PatientEvaluation;
import com.ironhack.evaluationclient.repository.PatientEvaluationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientEvaluationService {
    @Autowired
    private PatientEvaluationRepository patientEvaluationRepository;

    public PatientEvaluation create(PatientEvaluation patientEvaluation){
        return patientEvaluationRepository.save(patientEvaluation);
    }

    public PatientEvaluation findById(Integer id){
        return patientEvaluationRepository.findById(id).orElseThrow(()-> new IdNotFoundException("Evaluation not found"));
    }

    public List<PatientEvaluation> findByPatient(Integer id){
        return patientEvaluationRepository.findByPatient(id);
    }

    public void delete(Integer id){
        patientEvaluationRepository.deleteById(id);
    }
}
