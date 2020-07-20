package com.ironhack.edgeservice.enums;

public enum Category {
    A("Normo-nutrido"),
    B("Mal-nutrido"),
    C("Mal-nutrido grave");

    private final String description;

    Category(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
