package com.ironhack.treatmentclient.repository;

import com.ironhack.treatmentclient.model.Treatment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TreatmentRepository extends MongoRepository<Treatment, String> {
    public List<Treatment> findByPatient(Integer id);
}
