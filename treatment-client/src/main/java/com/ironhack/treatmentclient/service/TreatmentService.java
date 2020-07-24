package com.ironhack.treatmentclient.service;

import com.ironhack.treatmentclient.exception.IdNotFoundException;
import com.ironhack.treatmentclient.model.Treatment;
import com.ironhack.treatmentclient.repository.TreatmentRepository;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TreatmentService {

    @Autowired
    private TreatmentRepository treatmentRepository;

    public Treatment save(Treatment treatment){
        return treatmentRepository.save(treatment);
    }

    public List<Treatment> findByPatient(Integer id){
        return treatmentRepository.findByPatient(id);
    }

    public void delete(Treatment treatment){
        treatmentRepository.delete(treatment);
    }
}
