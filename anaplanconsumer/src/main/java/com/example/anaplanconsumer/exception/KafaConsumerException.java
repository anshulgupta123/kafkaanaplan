package com.example.anaplanconsumer.exception;

public class KafaConsumerException extends RuntimeException {

    private String message;

    public KafaConsumerException(String message) {
        super(message);
        this.message = message;

    }

    @Override
    public String getMessage() {
        return message;
    }
}
