package com.geektrust.backend.model;

import com.geektrust.backend.exception.InvalidInputException;
import com.geektrust.backend.utils.Constant;
import com.geektrust.backend.utils.EmailValidator;

public class Employee {
    private final String name;

    private final String emailAddress;

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
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((emailAddress == null) ? 0 : emailAddress.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Employee other = (Employee) obj;
        if (emailAddress == null) {
            if (other.emailAddress != null)
                return false;
        } else if (!emailAddress.equals(other.emailAddress))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Employee [emailAddress=" + emailAddress + ", name=" + name + "]";
    }
    
}
