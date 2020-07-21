package com.ironhack.evaluationclient.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ironhack.evaluationclient.enums.Category;
import com.ironhack.evaluationclient.enums.Metabolic;

import javax.persistence.*;

@Entity
public class DoctorEvaluation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Enumerated(EnumType.STRING)
    private Metabolic metabolic;
    @Enumerated(EnumType.STRING)
    private Category category;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="eval_patient_id")
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
    @JsonIgnore
    public PatientEvaluation getEvalPatient() {
        return evalPatient;
    }

    public void setEvalPatient(PatientEvaluation evalPatient) {
        this.evalPatient = evalPatient;
    }
}
