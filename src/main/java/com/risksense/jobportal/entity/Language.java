package com.risksense.jobportal.entity;

public enum Language {

    ENGLISH("ENGLISH"), FRENCH("FRENCH"), HINDI("HINDI"), TAMIL("TAMIL");

    private Language(String value) {
        this.value = value;
    }

    private String value;

    public String getValue() {
        return value;
    }
}
