package com.geektrust.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class RegistrationDto {
    private String regID;
    private final String emailAddress;
    private final String courseID;
    private boolean isAccepted;    
}
