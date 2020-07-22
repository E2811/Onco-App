package com.ironhack.evaluationclient.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ironhack.evaluationclient.enums.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
public class PatientEvaluation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Min(0)
    private double weight;
    @Enumerated(EnumType.STRING)
    private Intake intake;
    @Enumerated(EnumType.STRING)
    private Symptoms symptoms;
    @Enumerated(EnumType.STRING)
    private Activity ecog;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate review;
    @NotNull
    private Integer patient;
    @OneToOne(mappedBy = "evalPatient")
    private DoctorEvaluation evaluationDoctor;
    private boolean evaluated;

    public PatientEvaluation() {
        this.evaluated = false;
        this.review = LocalDate.now();
    }

    public PatientEvaluation(double weight, Intake intake, Symptoms symptoms, Activity ecog, @NotNull Integer patient) {
        this.weight = weight;
        this.intake = intake;
        this.symptoms = symptoms;
        this.ecog = ecog;
        this.review = LocalDate.now();
        this.patient = patient;
        this.evaluated = false;
    }

    public LocalDate getReview() {
        return review;
    }
    @JsonIgnore
    public DoctorEvaluation getEvaluationDoctor() {
        return evaluationDoctor;
    }

    public boolean isEvaluated() {
        return evaluated;
    }

    public void setEvaluated(boolean evaluated) {
        this.evaluated = evaluated;
    }

    public void setEvaluationDoctor(DoctorEvaluation evaluationDoctor) {
        this.evaluationDoctor = evaluationDoctor;
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
