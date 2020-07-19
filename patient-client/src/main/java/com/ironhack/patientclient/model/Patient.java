package com.ironhack.patientclient.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;

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
    @Min(0)
    private int age;
    @NotNull
    private Integer userID;
    @NotNull
    private Integer doctor;

    public Patient() {
    }

    public Patient(@NotNull @NotEmpty(message = "Name is empty") String name, String phone, @NotNull @Email(message = "Email invalid") String email, @NotNull @Min(0) int age, @NotNull Integer userID, @NotNull Integer doctor) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.age = age;
        this.userID = userID;
        this.doctor = doctor;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
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
