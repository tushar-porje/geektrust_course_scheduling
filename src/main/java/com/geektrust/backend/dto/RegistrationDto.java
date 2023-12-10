package com.geektrust.backend.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class RegistrationDto {
    private String regID;
    private final String emailAddress;
    private final String courseID;
    private boolean isAccepted;
    
    public RegistrationDto(String regID, String emailAddress, String courseID, boolean isAccepted) {
        this(emailAddress, courseID);
        this.regID = regID;
        this.isAccepted = isAccepted;
    }
    
    public RegistrationDto(String emailAddress, String courseID) {
        this.emailAddress = emailAddress;
        this.courseID = courseID;
    }    
}
