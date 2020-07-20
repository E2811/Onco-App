package com.ironhack.resultclient.service;

import com.ironhack.resultclient.model.Result;
import com.ironhack.resultclient.repository.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResultService {

    @Autowired
    private ResultRepository resultRepository;

    public Result findByEvaluation(Integer id){
        return resultRepository.findByEvaluation(id);
    }

    public void delete(Integer id){
        resultRepository.deleteById(id);
    }

    public Result create(Result result){
        return  resultRepository.save(result);
    }

}
