package com.ironhack.edgeservice.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ironhack.edgeservice.enums.Sex;

import javax.validation.constraints.*;
import java.time.LocalDate;

public class PatientMV {
    private Integer id;
    @NotNull
    @NotEmpty(message="Name is empty")
    private String name;
    @Pattern(regexp="(\\d{9})", message = "Enter a valid phone number")
    private String phone;
    @NotNull
    @Email(message = "Email invalid")
    private String email;
    @NotNull
    private Sex sex;
    @NotNull
    @Min(0)
    private double weight;
    @Min(0)
    private double height;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate birthday;
    @NotNull
    private Integer doctor;
    @NotNull
    @NotBlank(message = "username is empty")
    private String username;

    public PatientMV(Integer id, @NotNull @NotEmpty(message = "Name is empty") String name, @Pattern(regexp = "(\\d{9})", message = "Enter a valid phone number") String phone, @NotNull @Email(message = "Email invalid") String email, @NotNull Sex sex, @NotNull @Min(0) double weight, @Min(0) double height, LocalDate birthday, @NotNull Integer doctor, @NotNull @NotBlank(message = "username is empty") String username) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.sex = sex;
        this.weight = weight;
        this.height = height;
        this.birthday = birthday;
        this.doctor = doctor;
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

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }


    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public double getWeight() {
        return weight;
    }


    public double getHeight() {
        return height;
    }


    public LocalDate getBirthday() {
        return birthday;
    }


    public Integer getDoctor() {
        return doctor;
    }

    public void setDoctor(Integer doctor) {
        this.doctor = doctor;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
