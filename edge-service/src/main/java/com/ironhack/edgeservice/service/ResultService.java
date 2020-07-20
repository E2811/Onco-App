package com.ironhack.edgeservice.service;

import com.ironhack.edgeservice.client.ResultClient;
import com.ironhack.edgeservice.config.FeignBadResponseWrapper;
import com.ironhack.edgeservice.controller.dto.PatientMV;
import com.ironhack.edgeservice.enums.Sex;
import com.ironhack.edgeservice.model.DoctorEvaluation;
import com.ironhack.edgeservice.model.PatientEvaluation;
import com.ironhack.edgeservice.model.Result;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;

@Service
public class ResultService {

    @Autowired
    private ResultClient resultClient;
    @Autowired
    private PatientService patientService;
    @Autowired
    private EvaluationService evaluationService;

    @HystrixCommand(fallbackMethod = "createFake",  ignoreExceptions = FeignBadResponseWrapper.class)
    public Result create(Integer evaluationId){
        PatientEvaluation evaluation = evaluationService.findById(evaluationId);
        DoctorEvaluation doctorEvaluation = evaluationService.findByPatientEval(evaluationId);
        PatientMV patient = patientService.findById(evaluation.getPatient());
        double imc = evaluation.getWeight()/ (Math.pow(patient.getHeight(),2));
        double bodySurface = 3.207 *evaluation.getWeight()*(0.7825 - 0.01188*Math.log10(evaluation.getWeight()))*patient.getHeight()*0.3 ;
        double weightLoss = ((patient.getWeight()- evaluation.getWeight())/patient.getHeight())*100;
        double caloriesNeeded = 0;
        int age = Period.between(patient.getBirthday(),LocalDate.now()).getYears();
        if (patient.getSex() == Sex.F){
            caloriesNeeded = (665.1 + (9.56*evaluation.getWeight())+(1.85*patient.getHeight())-(4.98*age))*doctorEvaluation.getMetabolic().getPonderation()*evaluation.getEcog().getPonderation();
        }else{
            caloriesNeeded = 66.47 + (13.75*evaluation.getWeight())+(5.0*patient.getHeight())-(6.74*age);
        }
        Result result = new Result(imc, bodySurface, weightLoss, caloriesNeeded,evaluationId);
        return resultClient.create(result);
    }

    public Result createFake(Integer evaluationId){
        throw new NullPointerException("Result service is down. Try again Later");
    }
    @HystrixCommand(fallbackMethod = "deleteFake")
    public void delete(Integer id){
        resultClient.delete(id);
    }

    public void deleteFake(Integer id){
        throw new NullPointerException("Result service is down. Try again Later");
    }

    @HystrixCommand(fallbackMethod = "findByEvaluationFake")
    public Result findByEvaluation(Integer id){
        return resultClient.findByEvaluation(id);
    }

    public Result findByEvaluationFake(Integer id){
        throw new NullPointerException("Result service is down. Try again Later");
    }
}
