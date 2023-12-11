package com.geektrust.backend.service;

import com.geektrust.backend.dto.RegistrationDto;
import com.geektrust.backend.exception.InvalidInputException;

public interface RegistrationSevice{
    String create(RegistrationDto registrationDto);
    String cancelRegistration(String RegId)  throws InvalidInputException;
}
