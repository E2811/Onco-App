package com.ironhack.patientclient.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ironhack.patientclient.enums.Sex;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Entity
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @Enumerated(EnumType.STRING)
    private Sex sex;
    @NotNull
    @Min(0)
    private double weight;
    @Min(0)
    private double height;
    @NotNull
    private Integer userID;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate birthday;
    @NotNull
    private Integer doctor;

    public Patient() {
    }

    public Patient(@NotNull @NotEmpty(message = "Name is empty") String name, @Pattern(regexp = "(\\d{9})", message = "Enter a valid phone number") String phone, @NotNull @Email(message = "Email invalid") String email, @NotNull Sex sex, @NotNull @Min(0) double weight, @Min(0) double height, @NotNull Integer userID, LocalDate birthday, @NotNull Integer doctor) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.sex = sex;
        this.weight = weight;
        this.height = height;
        this.userID = userID;
        this.birthday = birthday;
        this.doctor = doctor;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
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

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public Integer getDoctor() {
        return doctor;
    }

    public void setDoctor(Integer doctor) {
        this.doctor = doctor;
    }
}
