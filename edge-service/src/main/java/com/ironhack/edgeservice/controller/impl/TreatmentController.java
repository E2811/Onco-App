package com.ironhack.edgeservice.controller.impl;

import com.ironhack.edgeservice.controller.dto.TreatmentDto;
import com.ironhack.edgeservice.model.Result;
import com.ironhack.edgeservice.model.Treatment;
import com.ironhack.edgeservice.service.TreatmentService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/treatment")
public class TreatmentController {

    @Autowired
    private TreatmentService treatmentService;

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DOCTOR') ")
    @PostMapping("/save")
    @ApiOperation(value = "Create treatment")
    @ResponseStatus(HttpStatus.CREATED)
    public Treatment createTreatment(@Validated @RequestBody TreatmentDto treatmentDto) {
        return treatmentService.create(treatmentDto);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DOCTOR') or hasRole('ROLE_PATIENT')")
    @GetMapping("/find_by_patient/{id}")
    @ApiOperation(value = "find treatment by patients id")
    @ResponseStatus(HttpStatus.OK)
    public List<Treatment> findByPatient(@PathVariable Integer id) {
        return treatmentService.findByPatient(id);
    }
}
