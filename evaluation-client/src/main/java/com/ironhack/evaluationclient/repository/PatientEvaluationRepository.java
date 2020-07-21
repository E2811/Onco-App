package com.ironhack.evaluationclient.repository;

import com.ironhack.evaluationclient.model.PatientEvaluation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientEvaluationRepository extends JpaRepository<PatientEvaluation, Integer> {
    List<PatientEvaluation> findByPatient(Integer id);
}
