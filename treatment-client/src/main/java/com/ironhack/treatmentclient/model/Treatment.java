package com.ironhack.treatmentclient.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ironhack.treatmentclient.enums.Type;
import jdk.jfr.Name;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
public class Treatment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Enumerated(EnumType.STRING)
    private Type type;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate revisionDate;
    @NotNull(message = "patient null")
    private Integer patient;

    public Treatment() {
        this.revisionDate = LocalDate.now();
    }

    public Treatment(Type type, Integer patient) {
        this.type = type;
        this.revisionDate = LocalDate.now();
        this.patient = patient;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public LocalDate getRevisionDate() {
        return revisionDate;
    }

    public void setRevisionDate(LocalDate revisionDate) {
        this.revisionDate = revisionDate;
    }

    public Integer getPatient() {
        return patient;
    }

    public void setPatient(Integer patient) {
        this.patient = patient;
    }
}
