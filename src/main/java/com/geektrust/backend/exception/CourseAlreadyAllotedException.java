package com.geektrust.backend.exception;

public class CourseAlreadyAllotedException extends RuntimeException{

    public CourseAlreadyAllotedException() {}

    public CourseAlreadyAllotedException(String message) {
        super(message);
    }
    
}
