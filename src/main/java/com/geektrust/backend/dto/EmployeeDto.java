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
    public String toString() {
        return "EmployeeDto [emailAddress=" + emailAddress + ", name=" + name + "]";
    }

    
}
