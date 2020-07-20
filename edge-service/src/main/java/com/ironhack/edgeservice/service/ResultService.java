package com.ironhack.edgeservice.service;

import com.ironhack.edgeservice.client.ResultClient;
import com.ironhack.edgeservice.controller.dto.PatientMV;
import com.ironhack.edgeservice.controller.dto.ResultDto;
import com.ironhack.edgeservice.enums.Sex;
import com.ironhack.edgeservice.model.Patient;
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
    public Result create(ResultDto resultDto){
        PatientEvaluation evaluation = evaluationService.findById(resultDto.getEvaluation());
        PatientMV patient = patientService.findById(evaluation.getPatient());
        double imc = patient.getWeight()/ (Math.pow(patient.getHeight(),2));
        double bodySurface = 3.207 *patient.getWeight()*(0.7825 - 0.01188*Math.log10(patient.getWeight()))*patient.getHeight()*0.3 ;
        double weightLoss = ((patient.getWeight()- resultDto.getWeight())/patient.getHeight())*100;
        double caloriesNeeded = 0;
        int age = Period.between(patient.getBirthday(),LocalDate.now()).getYears();
        if (patient.getSex() == Sex.F){
            caloriesNeeded = 665.1 + (9.56*patient.getWeight())+(1.85*patient.getHeight())-(4.98*age);
        }else{
            caloriesNeeded = 66.47 + (13.75*patient.getWeight())+(5.0*patient.getHeight())-(6.74*age);
        }
        Result result = new Result(imc, bodySurface, weightLoss, caloriesNeeded, resultDto.getEvaluation());
        return resultClient.create(result);
    }

    public Result createFake(ResultDto resultDto){
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
