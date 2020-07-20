package com.ironhack.edgeservice.controller.dto;

import com.ironhack.edgeservice.enums.Category;
import com.ironhack.edgeservice.enums.Metabolic;

import javax.validation.constraints.NotNull;

public class DoctorEvaluationDto {
    @NotNull
    private Metabolic metabolic;
    @NotNull
    private Category category;
    @NotNull
    private Integer evaluationId;

    public Metabolic getMetabolic() {
        return metabolic;
    }

    public void setMetabolic(Metabolic metabolic) {
        this.metabolic = metabolic;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Integer getEvaluationId() {
        return evaluationId;
    }

    public void setEvaluationId(Integer evaluationId) {
        this.evaluationId = evaluationId;
    }
}
