package com.ironhack.evaluationclient.controller.dto;

import com.ironhack.evaluationclient.enums.Category;
import com.ironhack.evaluationclient.enums.Metabolic;
import com.ironhack.evaluationclient.model.PatientEvaluation;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

public class DoctorEvaluationDto {

    @NotNull
    private Metabolic metabolic;
    @NotNull
    private Category category;
    @NotNull
    private Integer evaluationId;

    public DoctorEvaluationDto(@NotNull Metabolic metabolic, @NotNull Category category, @NotNull Integer evaluationId) {
        this.metabolic = metabolic;
        this.category = category;
        this.evaluationId = evaluationId;
    }

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
