package com.risksense.jobportal.entity;

public enum JobType {

    DEVELOPER("DEVELOPER"), TESTER("TESTER"), MANAGER("FULL TIME");

    private JobType(String value) {
        this.value = value;
    }

    private String value;

    public String getValue() {
        return value;
    }

}
