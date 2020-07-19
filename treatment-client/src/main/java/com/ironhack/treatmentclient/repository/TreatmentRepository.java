package com.ironhack.treatmentclient.repository;

import com.ironhack.treatmentclient.model.Treatment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TreatmentRepository extends JpaRepository<Treatment, Integer> {
    public List<Treatment> findByPatient(Integer id);
}
