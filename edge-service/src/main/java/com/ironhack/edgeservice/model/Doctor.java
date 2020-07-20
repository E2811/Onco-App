package com.ironhack.edgeservice.model;

import com.ironhack.edgeservice.enums.Specialty;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Doctor {

    private Integer id;

    @NotNull
    @NotEmpty(message="Name is empty")
    private String name;

    @NotNull
    private Integer userID;

    private Specialty specialty;
    @NotNull
    @Email(message = "Email invalid")
    private String email;

    public Doctor() {
    }

    public Doctor(@NotNull @NotEmpty(message = "Name is empty") String name, @NotNull Integer userID, Specialty specialty, @Email String email) {
        this.name = name;
        this.userID = userID;
        this.specialty = specialty;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
