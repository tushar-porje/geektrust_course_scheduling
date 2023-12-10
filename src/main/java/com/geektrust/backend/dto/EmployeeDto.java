package com.geektrust.backend.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class EmployeeDto {

    private String name;
    private final String emailAddress;
    
    public EmployeeDto(String name, String emailAddress) {
        this(emailAddress);
        this.name = name;
    }
    public EmployeeDto(String emailAddress) {
        this.emailAddress = emailAddress;
    }
    
}
