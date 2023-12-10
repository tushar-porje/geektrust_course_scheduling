package com.geektrust.backend.exception;

public class RegistrationCancelException extends RuntimeException{

    public RegistrationCancelException() {}

    public RegistrationCancelException(String message) {
        super(message);
    }
    
}
