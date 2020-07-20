package com.ironhack.edgeservice.enums;

public enum Activity {
    FULLY_ACTIVE(1.75),
    RESTRICTED(1.5),
    AMBULATORY(1.3),
    LIMITED_SELFCARE(1.2),
    DISABLED(1);

    private final double ponderation;

    Activity(double v) {
        this.ponderation = v;
    }

    public double getPonderation() {
        return ponderation;
    }
}
