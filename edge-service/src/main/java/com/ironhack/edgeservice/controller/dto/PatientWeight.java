package com.ironhack.edgeservice.controller.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class PatientWeight {
    @NotNull
    private Integer id;
    @NotNull
    @Min(0)
    private double weight;

    public PatientWeight(@NotNull Integer id, @NotNull @Min(0) double weight) {
        this.id = id;
        this.weight = weight;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
