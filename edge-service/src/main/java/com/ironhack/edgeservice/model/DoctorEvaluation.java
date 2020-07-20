package com.ironhack.edgeservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ironhack.edgeservice.enums.Category;
import com.ironhack.edgeservice.enums.Metabolic;

import javax.validation.constraints.NotNull;

public class DoctorEvaluation {


    private Integer id;
    private Metabolic metabolic;
    private Category category;
    private PatientEvaluation evalPatient;

    public DoctorEvaluation() {
    }

    public DoctorEvaluation(Metabolic metabolic, Category category, PatientEvaluation evalPatient) {
        this.metabolic = metabolic;
        this.category = category;
        this.evalPatient = evalPatient;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public PatientEvaluation getEvalPatient() {
        return evalPatient;
    }

    public void setEvalPatient(PatientEvaluation evalPatient) {
        this.evalPatient = evalPatient;
    }
}
