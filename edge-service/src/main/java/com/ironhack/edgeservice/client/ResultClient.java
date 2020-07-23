package com.ironhack.edgeservice.client;

import com.ironhack.edgeservice.model.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@FeignClient(name="result-client", url="https://onco-app-result-client.herokuapp.com/")
public interface ResultClient {

    @GetMapping("/result/by-evaluation/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result findByEvaluation(@PathVariable Integer id);

    @PostMapping("/result")
    @ResponseStatus(HttpStatus.CREATED)
    public Result create(@RequestBody @Valid Result result);

    @DeleteMapping("/result/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id);

}
