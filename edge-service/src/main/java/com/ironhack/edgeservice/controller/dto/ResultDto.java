package com.ironhack.edgeservice.controller.dto;

public class ResultDto {
    private Integer evaluation;
    private double weight;

    public Integer getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(Integer evaluation) {
        this.evaluation = evaluation;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public ResultDto(Integer evaluation, double weight) {
        this.evaluation = evaluation;
        this.weight = weight;
    }
}
