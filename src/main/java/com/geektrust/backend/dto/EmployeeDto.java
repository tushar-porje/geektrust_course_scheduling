package com.geektrust.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Data
@Setter
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class EmployeeDto {

    private String name;
    private final String emailAddress;    
}
