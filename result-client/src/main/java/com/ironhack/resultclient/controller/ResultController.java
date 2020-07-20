package com.ironhack.resultclient.controller;

import com.ironhack.resultclient.model.Result;
import com.ironhack.resultclient.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class ResultController {

    @Autowired
    private ResultService resultService;

    @GetMapping("/result/by-evaluation/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result findByEvaluation(@PathVariable Integer id){
        return resultService.findByEvaluation(id);
    }

    @PostMapping("/result")
    @ResponseStatus(HttpStatus.CREATED)
    public Result create(@RequestBody @Valid Result result){
        return resultService.create(result);
    }

    @DeleteMapping("/result/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id){
        resultService.delete(id);
    }
}
