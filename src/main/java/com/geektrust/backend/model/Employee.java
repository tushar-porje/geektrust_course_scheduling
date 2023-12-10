package com.geektrust.backend.model;

import com.geektrust.backend.exception.InvalidInputException;
import com.geektrust.backend.utils.Constant;
import com.geektrust.backend.utils.EmailValidator;

public class Employee {
    private final String name;

    private final String emailAddress;

    private String courseId;

    public String getName() {
        return name;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public Employee(String emailAddress) {
        if (!emailAddress.isEmpty() && EmailValidator.validate(emailAddress)) {
            this.name = emailAddress.substring(0, emailAddress.indexOf('@'));
            this.emailAddress = emailAddress;
        } else {
            throw new InvalidInputException(Constant.INPUT_DATA_ERROR_MESSAGE);
        }
    }

    @Override
    public String toString() {
        return "Employee [emailAddress=" + emailAddress + ", name=" + name + "]";
    }
    
}
