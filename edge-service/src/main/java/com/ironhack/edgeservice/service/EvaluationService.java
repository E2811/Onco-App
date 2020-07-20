package com.ironhack.edgeservice.service;

import com.ironhack.edgeservice.client.EvaluationClient;
import com.ironhack.edgeservice.config.FeignBadResponseWrapper;
import com.ironhack.edgeservice.controller.dto.DoctorEvaluationDto;
import com.ironhack.edgeservice.controller.dto.EvaluationMV;
import com.ironhack.edgeservice.controller.dto.PatientMV;
import com.ironhack.edgeservice.model.DoctorEvaluation;
import com.ironhack.edgeservice.model.Patient;
import com.ironhack.edgeservice.model.PatientEvaluation;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.exception.HystrixBadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EvaluationService {

    @Autowired
    private EvaluationClient evaluationClient;
    @Autowired
    private PatientService patientService;

    @HystrixCommand(fallbackMethod = "createFakeEval", ignoreExceptions = FeignBadResponseWrapper.class)
    public PatientEvaluation create(PatientEvaluation patientEvaluation){
        patientService.findById(patientEvaluation.getPatient());
        return evaluationClient.create(patientEvaluation);
    }

    public PatientEvaluation createFakeEval(PatientEvaluation patientEvaluation){
        throw new NullPointerException("Evaluation service is down. Try again Later");
    }

    @HystrixCommand(fallbackMethod = "createFakeDoctorEval", ignoreExceptions = FeignBadResponseWrapper.class)
    public DoctorEvaluation createDoctorEval(DoctorEvaluationDto doctorEvaluationDto){
        try {
            PatientEvaluation patientEvaluation = evaluationClient.findById(doctorEvaluationDto.getEvaluationId());
        }catch (HystrixBadRequestException e) {
            if (e instanceof FeignBadResponseWrapper) {
                throw new FeignBadResponseWrapper(((FeignBadResponseWrapper) e).getStatus(), ((FeignBadResponseWrapper) e).getHeaders(), ((FeignBadResponseWrapper) e).getBody());
            }
        }
        return evaluationClient.createDoctorEval(doctorEvaluationDto);
    }

    public DoctorEvaluation createFakeDoctorEval(DoctorEvaluationDto doctorEvaluationDto){
        throw new NullPointerException("Evaluation service is down. Try again Later");
    }

    @HystrixCommand(fallbackMethod = "findByPatientFake")
    public List<PatientEvaluation> findByPatient(Integer patientId){
        return evaluationClient.findByPatient(patientId);
    }

    public List<PatientEvaluation> findByPatientFake(Integer patientId){
        throw new NullPointerException("Evaluation service is down. Try again Later");
    }

    @HystrixCommand(fallbackMethod = "findByIdFake", ignoreExceptions = FeignBadResponseWrapper.class)
    public PatientEvaluation findById(Integer id){
        PatientEvaluation patientEvaluation = null;
        try {
            patientEvaluation = evaluationClient.findById(id);
        }catch (HystrixBadRequestException e) {
            if (e instanceof FeignBadResponseWrapper) {
                throw new FeignBadResponseWrapper(((FeignBadResponseWrapper) e).getStatus(), ((FeignBadResponseWrapper) e).getHeaders(), ((FeignBadResponseWrapper) e).getBody());
            }
        }
        return patientEvaluation;
    }

    public PatientEvaluation findByIdFake(Integer id){
        throw new NullPointerException("Evaluation service is down. Try again Later");
    }

    @HystrixCommand(fallbackMethod = "findByPatientEvalFake")
    public DoctorEvaluation findByPatientEval(Integer id){
        return evaluationClient.findByEvaluation(id);
    }

    public DoctorEvaluation findByPatientEvalFake(Integer id){
        throw new NullPointerException("Evaluation service is down. Try again Later");
    }

    @HystrixCommand(fallbackMethod = "deleteDoctorEvalFake")
    public void deleteDoctorEval(Integer id){
        evaluationClient.deleteDoctorEvaluation(id);
    }

    public void deleteDoctorEvalFake(Integer id){
        throw new NullPointerException("Evaluation service is down. Try again Later");
    }

    @HystrixCommand(fallbackMethod = "deletePatientEvalFake")
    public void deletePatientEval(Integer id){
        evaluationClient.delete(id);
    }

    public void deletePatientEvalFake(Integer id){
        throw new NullPointerException("Evaluation service is down. Try again Later");
    }

    @HystrixCommand(fallbackMethod = "findCompleteFake")
    public List<EvaluationMV> findCompleteEval(Integer id){
        List<EvaluationMV> evaluationMV = new ArrayList<>();
        evaluationClient.findByPatient(id).forEach(patientEvaluation -> {
            DoctorEvaluation doctorEvaluation = evaluationClient.findByEvaluation(patientEvaluation.getId());
            if (doctorEvaluation != null){
                evaluationMV.add(new EvaluationMV(patientEvaluation.getId(), patientEvaluation.getWeight(), patientEvaluation.getIntake(), patientEvaluation.getSymptoms(), patientEvaluation.getEcog(), doctorEvaluation.getMetabolic(), doctorEvaluation.getCategory(), patientEvaluation.getReview(), patientEvaluation.getPatient()));
            }
        });
        return evaluationMV;
    }

    public List<EvaluationMV> findCompleteFake(Integer id){
        throw new NullPointerException("Evaluation service is down. Try again Later");
    }
}
