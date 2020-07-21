package com.ironhack.evaluationclient.repository;

import com.ironhack.evaluationclient.model.DoctorEvaluation;
import com.ironhack.evaluationclient.model.PatientEvaluation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorEvaluationRepository extends JpaRepository<DoctorEvaluation, Integer> {
    DoctorEvaluation findByEvalPatient(PatientEvaluation patientEvaluation);
}
