package com.ironhack.edgeservice.controller.impl;

import com.ironhack.edgeservice.controller.dto.PatientDto;
import com.ironhack.edgeservice.controller.dto.PatientMV;
import com.ironhack.edgeservice.controller.dto.ResultDto;
import com.ironhack.edgeservice.model.Result;
import com.ironhack.edgeservice.service.ResultService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/result")
public class ResultController {

    @Autowired
    private ResultService resultService;

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DOCTOR') or hasRole('ROLE_PATIENT')")
    @PostMapping("/save")
    @ApiOperation(value = "Create result")
    @ResponseStatus(HttpStatus.CREATED)
    public Result createPatient(@Validated @RequestBody ResultDto resultDto) {
        return resultService.create(resultDto);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DOCTOR') or hasRole('ROLE_PATIENT')")
    @GetMapping("/find_by_evaluation/{id}")
    @ApiOperation(value = "find result by evaluation")
    @ResponseStatus(HttpStatus.OK)
    public Result findByEvaluation(@PathVariable Integer id) {
        return resultService.findByEvaluation(id);
    }

}
