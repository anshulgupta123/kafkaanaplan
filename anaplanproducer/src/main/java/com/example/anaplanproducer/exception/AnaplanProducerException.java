package com.example.anaplanproducer.exception;

public class AnaplanProducerException extends RuntimeException {

    private String message;

    public AnaplanProducerException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
