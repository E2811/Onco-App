package com.ironhack.edgeservice.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ironhack.edgeservice.enums.Activity;
import com.ironhack.edgeservice.enums.Intake;
import com.ironhack.edgeservice.enums.Symptoms;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class PatientEvaluation {

    private Integer id;
    @NotNull
    private double weight;
    @NotNull
    private Intake intake;
    @NotNull
    private Symptoms symptoms;
    @NotNull
    private Activity ecog;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate review;
    @NotNull
    private Integer patient;

    public PatientEvaluation() {
        this.review = LocalDate.now();
    }

    public PatientEvaluation(double weight, Intake intake, Symptoms symptoms, Activity ecog, @NotNull Integer patient) {
        this.weight = weight;
        this.intake = intake;
        this.symptoms = symptoms;
        this.ecog = ecog;
        this.review = LocalDate.now();
        this.patient = patient;
    }

    public LocalDate getReview() {
        return review;
    }

    public void setReview(LocalDate review) {
        this.review = review;
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

    public Intake getIntake() {
        return intake;
    }

    public void setIntake(Intake intake) {
        this.intake = intake;
    }

    public Symptoms getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(Symptoms symptoms) {
        this.symptoms = symptoms;
    }

    public Activity getEcog() {
        return ecog;
    }

    public void setEcog(Activity ecog) {
        this.ecog = ecog;
    }

    public Integer getPatient() {
        return patient;
    }

    public void setPatient(Integer patient) {
        this.patient = patient;
    }

}
