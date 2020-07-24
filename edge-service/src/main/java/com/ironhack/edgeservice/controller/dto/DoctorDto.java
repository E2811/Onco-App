package com.ironhack.edgeservice.controller.dto;

import com.ironhack.edgeservice.enums.Specialty;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class DoctorDto {

    @NotNull
    @NotEmpty(message="Name is empty")
    private String name;

    @NotNull
    private Specialty specialty;
    @NotNull
    @Email(message = "Email format invalid")
    private String email;
    @NotNull
    private String username;

    @NotNull
    private String password;

    public DoctorDto() {
    }

    public DoctorDto(@NotNull @NotEmpty(message = "Name is empty") String name, @NotNull Specialty specialty, @Email(message = "Email format invalid") String email, @NotNull String username, @NotNull String password) {
        this.name = name;
        this.specialty = specialty;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
}
