package com.ironhack.doctorclient.repository;

import com.ironhack.doctorclient.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Integer> {
    Doctor findByUserID(Integer id);
}
