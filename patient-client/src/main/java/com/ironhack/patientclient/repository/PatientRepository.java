package com.ironhack.patientclient.repository;

import com.ironhack.patientclient.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient,Integer> {
    List<Patient> findByDoctor(Integer id);
    Patient findByUserID(Integer id);
}
