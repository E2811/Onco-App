package com.ironhack.edgeservice.enums;

public enum Metabolic {
    NONE(1),
    MINOR(1.1),
    MODERATE(1.2),
    HIGH(1.3);
    
    private final double ponderation;

    Metabolic(double v) {
        this.ponderation = v;
    }

    public double getPonderation() {
        return ponderation;
    }
}
