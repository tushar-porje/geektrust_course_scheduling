package com.geektrust.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
@AllArgsConstructor
public class Registration {
    private String regID;
    private final String emailAddress;
    private final String courseID;
    private boolean isAccepted;
        
}
