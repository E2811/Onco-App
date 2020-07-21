package com.ironhack.edgeservice.service;

import com.ironhack.edgeservice.client.TreatmentClient;
import com.ironhack.edgeservice.config.FeignBadResponseWrapper;
import com.ironhack.edgeservice.model.Treatment;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.exception.HystrixBadRequestException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TreatmentService {

    @Autowired
    private TreatmentClient treatmentClient;
    @Autowired
    private PatientService patientService;

    private static final Logger LOGGER = LogManager.getLogger(TreatmentService.class);

    @HystrixCommand(fallbackMethod = "createFakeTreatment", ignoreExceptions = FeignBadResponseWrapper.class)
    public Treatment create(Treatment treatment){
        LOGGER.info("[INIT] create new treatment");
        patientService.findById(treatment.getPatient());
        LOGGER.info("[EXIT] create new treatment");
        return treatmentClient.create(treatment);
    }

    public Treatment createFakeTreatment(Treatment treatment){
        throw new NullPointerException("Treatment service is down. Try again Later");
    }

    @HystrixCommand(fallbackMethod = "findByPatientFake")
    public List<Treatment> findByPatient(Integer id){
        LOGGER.info("[INIT] find list of treatment by patient");
        return treatmentClient.findByPatient(id);
    }

    public List<Treatment> findByPatientFake(Integer id){
        throw new NullPointerException("Treatment service is down. Try again Later");
    }


}
