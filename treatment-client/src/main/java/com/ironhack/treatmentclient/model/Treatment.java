package com.ironhack.treatmentclient.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ironhack.treatmentclient.enums.Type;
import jdk.jfr.Name;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Document
public class Treatment {

    @Id
    private String id;
    private Type type;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate revisionDate;
    @NotNull(message = "patient null")
    private Integer patient;

    public Treatment() {
    }

    public Treatment(Type type, LocalDate revisionDate, Integer patient) {
        this.type = type;
        this.revisionDate = revisionDate;
        this.patient = patient;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
