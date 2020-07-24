package com.ironhack.edgeservice.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ironhack.edgeservice.enums.Type;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class TreatmentDto {

    @NotNull
    private Type type;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private LocalDateTime revisionDate;
    @NotNull(message = "patient null")
    private Integer patient;

    public TreatmentDto(@NotNull Type type, LocalDateTime revisionDate, @NotNull(message = "patient null") Integer patient) {
        this.type = type;
        this.revisionDate = revisionDate;
        this.patient = patient;
    }

    public Type getType() {
        return type;
    }

    public LocalDateTime getRevisionDate() {
        return revisionDate;
    }

    public Integer getPatient() {
        return patient;
    }

    public void setPatient(Integer patient) {
        this.patient = patient;
    }
}
