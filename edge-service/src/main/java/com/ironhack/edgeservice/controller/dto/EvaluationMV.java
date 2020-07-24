package com.ironhack.edgeservice.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ironhack.edgeservice.enums.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class EvaluationMV {

    private Integer id;
    @NotNull
    private double weight;
    @NotNull
    private Intake intake;
    @NotNull
    private Symptoms symptoms;
    @NotNull
    private Activity ecog;
    @NotNull
    private Metabolic metabolic;
    @NotNull
    private Category category;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate review;
    @NotNull
    private Integer patient;
    private boolean evaluated;

    public EvaluationMV(Integer id, boolean evaluated, @NotNull double weight, @NotNull Intake intake, @NotNull Symptoms symptoms, @NotNull Activity ecog, Metabolic metabolic, Category category, LocalDate review, @NotNull Integer patient) {
        this.id = id;
        this.weight = weight;
        this.intake = intake;
        this.symptoms = symptoms;
        this.ecog = ecog;
        this.metabolic = metabolic;
        this.category = category;
        this.review = review;
        this.patient = patient;
        this.evaluated = evaluated;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }



    public Symptoms getSymptoms() {
        return symptoms;
    }


    public Activity getEcog() {
        return ecog;
    }


    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }


    public Integer getPatient() {
        return patient;
    }

    public void setPatient(Integer patient) {
        this.patient = patient;
    }
}
