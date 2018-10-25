package com.risksense.jobportal.exception;

public class KafkaCommunicationException extends Throwable {

    public KafkaCommunicationException(String message) {
        super(message);
    }

    public KafkaCommunicationException(String message, Throwable cause) {
        super(message, cause);
    }
}
