package com.ironhack.evaluationclient.service;

import com.ironhack.evaluationclient.controller.dto.DoctorEvaluationDto;
import com.ironhack.evaluationclient.exception.IdNotFoundException;
import com.ironhack.evaluationclient.model.DoctorEvaluation;
import com.ironhack.evaluationclient.model.PatientEvaluation;
import com.ironhack.evaluationclient.repository.DoctorEvaluationRepository;
import com.ironhack.evaluationclient.repository.PatientEvaluationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.util.List;

@Service
public class DoctorEvaluationService {

    @Autowired
    private DoctorEvaluationRepository doctorEvaluationRepository;
    @Autowired
    private PatientEvaluationService patientEvaluationService;
    @Autowired
    private PatientEvaluationRepository patientEvaluationRepository;

    public DoctorEvaluation create(DoctorEvaluationDto doctorEvaluationDto){
        PatientEvaluation patientEvaluation = patientEvaluationService.findById(doctorEvaluationDto.getEvaluationId());
        DoctorEvaluation doctorEvaluation = new DoctorEvaluation(doctorEvaluationDto.getMetabolic(), doctorEvaluationDto.getCategory(), patientEvaluation);
        patientEvaluation.setEvaluationDoctor(doctorEvaluation);
        return doctorEvaluationRepository.save(doctorEvaluation);
    }

    public DoctorEvaluation findById(Integer id){
        return doctorEvaluationRepository.findById(id).orElseThrow(()-> new IdNotFoundException("Evaluation not found"));
    }

    public DoctorEvaluation findByEvaluation(Integer id){
        PatientEvaluation patientEvaluation = patientEvaluationService.findById(id);
        return doctorEvaluationRepository.findByEvalPatient(patientEvaluation);
    }

    public void delete(Integer id){
        doctorEvaluationRepository.deleteById(id);
    }
}
