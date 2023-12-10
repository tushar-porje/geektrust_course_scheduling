package com.geektrust.backend.exception;

public class CourseCanceledException extends RuntimeException{

    public CourseCanceledException() {}

    public CourseCanceledException(String message) {
        super(message);
    }
    
}
