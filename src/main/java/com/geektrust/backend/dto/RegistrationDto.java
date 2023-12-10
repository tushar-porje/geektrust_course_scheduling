package com.geektrust.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class RegistrationDto {
    private String regID;
    private final String emailAddress;
    private final String courseID;
    private boolean isAccepted;    
}
