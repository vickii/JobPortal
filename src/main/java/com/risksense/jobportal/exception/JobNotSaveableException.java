package com.risksense.jobportal.exception;

public class JobNotSaveableException extends RuntimeException {

    public JobNotSaveableException(String message) {
        super(message);
    }

    public JobNotSaveableException(String message, Throwable cause) {
        super(message, cause);
    }
}
