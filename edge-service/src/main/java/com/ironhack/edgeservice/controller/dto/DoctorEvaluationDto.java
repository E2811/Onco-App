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

    public DoctorEvaluationDto(@NotNull Metabolic metabolic, @NotNull Category category, @NotNull Integer evaluationId) {
        this.metabolic = metabolic;
        this.category = category;
        this.evaluationId = evaluationId;
    }

    public Metabolic getMetabolic() {
        return metabolic;
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

}
