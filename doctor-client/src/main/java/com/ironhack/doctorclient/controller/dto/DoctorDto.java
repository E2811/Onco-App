package com.ironhack.doctorclient.controller.dto;

import com.ironhack.doctorclient.enums.Specialty;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class DoctorDto {

    @NotNull
    @NotEmpty(message="Name is empty")
    private String name;

    @NotNull
    private Integer userID;

    @Enumerated(EnumType.STRING)
    private Specialty specialty;
    @Email(message = "Email format invalid")
    private String email;

    public DoctorDto() {
    }

    public DoctorDto(@NotNull @NotEmpty(message = "Name is empty") String name, @NotNull Integer userID, Specialty specialty, @Email(message = "Email format invalid") String email) {
        this.name = name;
        this.userID = userID;
        this.specialty = specialty;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public Specialty getSpecialty() {
        return specialty;
    }

    public void setSpecialty(Specialty specialty) {
        this.specialty = specialty;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
