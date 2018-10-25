package com.risksense.jobportal.entity;

public enum Availability {

    HOURLY("HOURLY"), PART_TIME("PART TIME"), FULL_TIME("FULL TIME");

    private Availability(String value) {
        this.value = value;
    }

    private String value;

    public String getValue() {
        return value;
    }
}
