package com.ironhack.edgeservice.client;

import com.ironhack.edgeservice.model.Treatment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@FeignClient(name = "treatment-client")
public interface TreatmentClient {

    @PostMapping("/treatment/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Treatment create(@RequestBody @Valid Treatment treatment);

    @GetMapping("/treatment/byPatient/{patientId}")
    public List<Treatment> findByPatient(@PathVariable Integer patientId);

    @GetMapping("/treatment/{id}")
    public Treatment findById(@PathVariable String id);
}
