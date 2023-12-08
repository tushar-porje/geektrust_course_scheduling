package com.geektrust.backend.dto;

public class EmployeeDto {

    private String name;
    private final String emailAddress;
    
    public EmployeeDto(String name, String emailAddress) {
        this.name = name;
        this.emailAddress = emailAddress;
    }
    public EmployeeDto(String emailAddress) {
        this.emailAddress = emailAddress;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmailAddress() {
        return emailAddress;
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
        EmployeeDto other = (EmployeeDto) obj;
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
        return "EmployeeDto [emailAddress=" + emailAddress + ", name=" + name + "]";
    }

    
}
