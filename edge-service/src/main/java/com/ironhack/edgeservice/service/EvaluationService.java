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
import io.swagger.models.auth.In;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

    private static final Logger LOGGER = LogManager.getLogger(EvaluationService.class);

    @HystrixCommand(fallbackMethod = "createFakeEval", ignoreExceptions = FeignBadResponseWrapper.class)
    public PatientEvaluation create(PatientEvaluation patientEvaluation){
        LOGGER.info("[INIT] Create new evaluation");
        LOGGER.info("[INIT] Check that associated patient exists");
        patientService.findById(patientEvaluation.getPatient());
        LOGGER.info("[EXIT] Create new evaluation");
        return evaluationClient.create(patientEvaluation);
    }

    public PatientEvaluation createFakeEval(PatientEvaluation patientEvaluation){
        throw new NullPointerException("Evaluation service is down. Try again Later");
    }

    @HystrixCommand(fallbackMethod = "createFakeDoctorEval", ignoreExceptions = FeignBadResponseWrapper.class)
    public DoctorEvaluation createDoctorEval(DoctorEvaluationDto doctorEvaluationDto){
        LOGGER.info("[INIT] Complete evaluation with doctor part");
        try {
            PatientEvaluation patientEvaluation = evaluationClient.findById(doctorEvaluationDto.getEvaluationId());
        }catch (HystrixBadRequestException e) {
            if (e instanceof FeignBadResponseWrapper) {
                throw new FeignBadResponseWrapper(((FeignBadResponseWrapper) e).getStatus(), ((FeignBadResponseWrapper) e).getHeaders(), ((FeignBadResponseWrapper) e).getBody());
            }
        }
        LOGGER.info("[EXIT] Complete evaluation with doctor part");
        return evaluationClient.createDoctorEval(doctorEvaluationDto);
    }

    public DoctorEvaluation createFakeDoctorEval(DoctorEvaluationDto doctorEvaluationDto){
        throw new NullPointerException("Evaluation service is down. Try again Later");
    }

    @HystrixCommand(fallbackMethod = "findByPatientFake")
    public List<PatientEvaluation> findByPatient(String username){
        LOGGER.info("[INIT] find List of evaluations by patient username");
        PatientMV patient = patientService.findByUsername(username);
        return evaluationClient.findByPatient(patient.getId());
    }

    public List<PatientEvaluation> findByPatientFake(String username){
        throw new NullPointerException("Evaluation service is down. Try again Later");
    }

    @HystrixCommand(fallbackMethod = "findByIdFake", ignoreExceptions = FeignBadResponseWrapper.class)
    public PatientEvaluation findById(Integer id){
        LOGGER.info("[INIT] find evaluation by its id");
        PatientEvaluation patientEvaluation = null;
        try {
            patientEvaluation = evaluationClient.findById(id);
        }catch (HystrixBadRequestException e) {
            if (e instanceof FeignBadResponseWrapper) {
                throw new FeignBadResponseWrapper(((FeignBadResponseWrapper) e).getStatus(), ((FeignBadResponseWrapper) e).getHeaders(), ((FeignBadResponseWrapper) e).getBody());
            }
        }
        LOGGER.info("[EXIT] find evaluation by its id");
        return patientEvaluation;
    }

    public PatientEvaluation findByIdFake(Integer id){
        throw new NullPointerException("Evaluation service is down. Try again Later");
    }

    @HystrixCommand(fallbackMethod = "findByPatientEvalFake")
    public DoctorEvaluation findByPatientEval(Integer id){
        LOGGER.info("[INIT] find doctor evaluation by patient evaluation id");
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
        LOGGER.info("[INIT] delete evaluation");
        evaluationClient.delete(id);
        LOGGER.info("[EXIT] delete evaluation");
    }

    public void deletePatientEvalFake(Integer id){
        throw new NullPointerException("Evaluation service is down. Try again Later");
    }

    @HystrixCommand(fallbackMethod = "findCompleteFake")
    public List<EvaluationMV> findCompleteEval(String username){
        LOGGER.info("[INIT] find complete evaluation by patient username ");
        PatientMV patient = patientService.findByUsername(username);
        List<EvaluationMV> evaluationMV = new ArrayList<>();
        evaluationClient.findByPatient(patient.getId()).forEach(patientEvaluation -> {
            DoctorEvaluation doctorEvaluation = evaluationClient.findByEvaluation(patientEvaluation.getId());
            if (doctorEvaluation != null){
                evaluationMV.add(new EvaluationMV(patientEvaluation.getId(), patientEvaluation.isEvaluated(), patientEvaluation.getWeight(), patientEvaluation.getIntake(), patientEvaluation.getSymptoms(), patientEvaluation.getEcog(), doctorEvaluation.getMetabolic(), doctorEvaluation.getCategory(), patientEvaluation.getReview(), patientEvaluation.getPatient()));
            }
        });
        LOGGER.info("[EXIT] find complete evaluation by patient username ");
        return evaluationMV;
    }

    public List<EvaluationMV> findCompleteFake(String username){
        throw new NullPointerException("Evaluation service is down. Try again Later");
    }

    @HystrixCommand(fallbackMethod = "updateFake")
    public void update(Integer id){
        evaluationClient.update(id);
    }
    public void updateFake(Integer id){
        throw new NullPointerException("Evaluation service is down. Try again Later");
    }
}
