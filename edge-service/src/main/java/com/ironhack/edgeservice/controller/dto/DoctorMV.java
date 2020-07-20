package com.ironhack.edgeservice.controller.dto;

import com.ironhack.edgeservice.enums.Specialty;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class DoctorMV {

    private Integer id;

    @NotNull
    @NotEmpty(message="Name is empty")
    private String name;

    @NotNull
    private Specialty specialty;
    @Email(message = "Email format invalid")
    private String email;
    @NotNull
    private String username;

    public DoctorMV(Integer id, @NotNull @NotEmpty(message = "Name is empty") String name, @NotNull Specialty specialty, @Email(message = "Email format invalid") String email, @NotNull String username) {
        this.id = id;
        this.name = name;
        this.specialty = specialty;
        this.email = email;
        this.username = username;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
