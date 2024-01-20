package com.geektrust.backend.model;

import lombok.Data;
import com.geektrust.backend.exception.InvalidInputException;
import com.geektrust.backend.utils.Constant;
import com.geektrust.backend.utils.EmailValidator;

@Data
public class Employee {
    private final String name;

    private final String emailAddress;

    public Employee(String emailAddress) {
        if (!emailAddress.isEmpty() && EmailValidator.validate(emailAddress)) {
            this.name = emailAddress.substring(0, emailAddress.indexOf(Constant.EMAILSEPERATOR));
            this.emailAddress = emailAddress;
        } else {
            throw new InvalidInputException(Constant.INPUT_DATA_ERROR_MESSAGE);
        }
    }  
}
