package com.geektrust.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Registration {
    private String regID;
    private final String emailAddress;
    private final String courseID;
    private boolean isAccepted;
        
}
