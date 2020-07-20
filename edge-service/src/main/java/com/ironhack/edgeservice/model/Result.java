package com.ironhack.edgeservice.model;

import javax.validation.constraints.Min;

public class Result {

    private Integer id;
    @Min(0)
    private double IMC;
    @Min(0)
    private double bodySurface;
    private double weightLoss;
    @Min(0)
    private double caloriesNeeded;
    private Integer evaluation;

    public Result() {
    }

    public Result(@Min(0) double IMC, @Min(0) double bodySurface, double weightLoss, @Min(0) double caloriesNeeded, Integer evaluation) {
        this.IMC = IMC;
        this.bodySurface = bodySurface;
        this.weightLoss = weightLoss;
        this.caloriesNeeded = caloriesNeeded;
        this.evaluation = evaluation;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getIMC() {
        return IMC;
    }

    public void setIMC(double IMC) {
        this.IMC = IMC;
    }

    public double getBodySurface() {
        return bodySurface;
    }

    public void setBodySurface(double bodySurface) {
        this.bodySurface = bodySurface;
    }

    public double getWeightLoss() {
        return weightLoss;
    }

    public void setWeightLoss(double weightLoss) {
        this.weightLoss = weightLoss;
    }

    public double getCaloriesNeeded() {
        return caloriesNeeded;
    }

    public void setCaloriesNeeded(double caloriesNeeded) {
        this.caloriesNeeded = caloriesNeeded;
    }

    public Integer getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(Integer evaluation) {
        this.evaluation = evaluation;
    }
}
