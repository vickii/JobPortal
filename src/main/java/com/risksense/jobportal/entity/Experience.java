package com.risksense.jobportal.entity;

public enum Experience {

    FRESHER("FRESHER"), MIDDLELEVEL("MIDDLELEVEL"), SENIOR("SENIOR"), MANAGER("MANAGER");

    private Experience(String value) {
        this.value = value;
    }

    private String value;

    public String getValue() {
        return value;
    }
}
