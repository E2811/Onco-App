package com.ironhack.resultclient.repository;

import com.ironhack.resultclient.model.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResultRepository extends JpaRepository<Result, Integer> {
    Result findByEvaluation(Integer id);
}
